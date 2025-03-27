package com.example.genre;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private String WEB_CLIENT_URL = System.getenv("WEB_CLIENT_URL");

    private final WebClient webClient;

    private final GenreRepository genreRepo;

    public GenreController(WebClient.Builder webClientBuilder, GenreRepository genreRepo) {
        this.webClient = webClientBuilder.baseUrl(WEB_CLIENT_URL).build();
        this.genreRepo = genreRepo;
    }

    @PostMapping
    public Genre createGenre(@RequestBody Genre genre){
        return genreRepo.save(genre);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody Genre newGenre){
        Optional<Genre> optionalGenre = genreRepo.findById(id);
        if(!optionalGenre.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Genre genre = optionalGenre.get();
        genre.setName(newGenre.getName());
        genre.setDescription(newGenre.getDescription());
        Genre updatedGenre = genreRepo.save(genre);
        return ResponseEntity.ok(updatedGenre);
    }

    @GetMapping
    public Flux<Genre> getAllGenres(){
        System.out.println("all genres");
        return Flux.fromIterable(genreRepo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<Genre>> getGenreById(@PathVariable Long id){
        return genreRepo.findById(id)
                .map(genre -> ResponseEntity.ok(Mono.just(genre)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        if(genreRepo.existsById(id)){
            genreRepo.deleteById(id);
            System.out.println("deleted");
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //movies/{id} fredriks endpoints
    @GetMapping("/{id}/movies")
    public Mono<ResponseEntity<GenreResponse>> getMoviesByTheGenreId(@PathVariable Long id){
        return genreRepo.findById(id)
                .map(genre ->{
                    return webClient.get()
                            .uri(("/movies/genre/" + id))
                            .retrieve()
                            .bodyToFlux(Movie.class)
                            .collectList()
                            .map(movies -> {
                                GenreResponse response = new GenreResponse();
                                response.setGenre(genre);
                                response.setMovies(movies);
                                return ResponseEntity.ok(response);
                            });
                }).orElseGet(() -> Mono.just(ResponseEntity.notFound().build()));
    }

}
