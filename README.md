# MicroServices med Spring Boot och MySQL

### Ett grupparbete tillsammans med Fredrik, Ivana och Linda


## LATHUND MED CRUD & ENDPOINTS

### För att köra behövs även movie-service och miljövaribeler
- DB_URL - URL till din databas tillexempel: jdbc:mysql://localhost:3306/genredb
- DB_USER - ditt användarnamn
- DB_PASSWORD - ditt lösenord
- WEB_CLIENT_URL - URL till din web-client, movie-client: http://localhost:8080

````json
{ 
        "name": "namnet på genre",
	      "description": "beskrivning på genre"
    }
````

| COMMAND | ENDPOINTS            | OPERATIONER                                            |
|:--------|----------------------|:-------------------------------------------------------|
| Post    | /genre               | Lägg till en ny genre                                  |
| Put     | /genre/{id}          | Uppdatera en vald genre                                |
| Delete  | /genre({id}          | Tar bort en vald genre                                 |
| Get     | /genre               | Visa alla genres                                       |
| Get     | /genre/{id}          | Visa en specifik genre med ett id                      |
| Get     | /genre/{id}/movies   | Visa alla filmer under en gerne med ett valda gerne id |
