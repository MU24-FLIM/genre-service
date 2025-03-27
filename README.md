## LATHUND MED CRUD & ENDPOINTS

    { 
        "name": ”namnet på genre”,
	      ”description”: ”beskrivning på genre”
    }


|     CRUD  |  ENDPOINTS           |  Förklaring vad olika saker gör                            |
|:----------|-----------------------|:----------------------------------------------------------|
|       Post|  /genre               |  Lägg till en ny genre                                    |
|        Put|  /genre/{id}          |  Uppdatera en vald genre                                  |
|     Delete|  /genre({id}          |  Tabort en vald genre                                     |
|        Get|  /genre               |  Visa alla genres                                         |
|        Get|  /genre/{id}          |  Visa en specifik genre med ett id                        |
|        Get|  /genre/{id}/movies   |  Visa alla filmer under en gerne med ett valda gerne id   |
