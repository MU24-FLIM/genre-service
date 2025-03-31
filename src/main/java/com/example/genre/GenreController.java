package com.example.genre;

import jakarta.validation.Valid;
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

    //För att göra en ny Genre
    @PostMapping
    public Genre createGenre(@RequestBody @Valid Genre genre){
        return genreRepo.save(genre);
    }


    //För att uppdatera en Genre med ett id
    @PutMapping("/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable Long id, @RequestBody @Valid Genre newGenre){
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


    //För att hitta all inlagda Genres
    @GetMapping
    public Flux<Genre> getAllGenres(){
        return Flux.fromIterable(genreRepo.findAll());
    }

    //För hitta en Genre med ett id
    @GetMapping("/{id}")
    public ResponseEntity<Mono<Genre>> getGenreById(@PathVariable Long id){
        return genreRepo.findById(id)
                .map(genre -> ResponseEntity.ok(Mono.just(genre)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //För att tabort en Genre med ett id
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id){
        if(genreRepo.existsById(id)){
            genreRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }


    //För att hitta alla filmer som är sparade under en specifik Genre med hjälp att Genre Id
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

    //En metod för att hjälpa Fredriks app att inte kunna lägga till filmer till en Genre som inte finns
    @GetMapping("/exists/{id}")
    public boolean genreExists(@PathVariable Long id) {
        return genreRepo.existsById(id);
    }

}
