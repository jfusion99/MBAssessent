# MB Assessment
This is a sample Spring Boot application.

* Java Version : 17
* Spring Boot version : 3.1.4
* Database connection : MSSQL *(Configure database setup in file, application.properties)
  * Database name : TESTDB
* APIs :
  1. Get all customers
  2. Create customer
  3. Update customer
  4. Delete customer
  5. Get customers with pagination
  6. Get movies (Nest API)

## Restful API
 
### i. Get all customers

* #### Request:
`GET /api/v1/customer`

* #### Response
    * #### Success
          Code : 200 Success
          Content : 
            [
                {
                    "id": 1,
                    "name": "Danny",
                    "email": "danny@gmail.com",
                    "contactNo": "0123456788",
                    "dob": "2023-10-10"
                },
                {
                    "id": 3,
                    "name": "Ken",
                    "email": "ken@gmail.com",
                    "contactNo": "0123456788",
                    "dob": "2023-10-10"
                }
            ]


### ii. Create customer

* #### Request
`POST /api/v1/customer`

* #### Data Params
Request body :

    {
        "name": "",
        "email": "@gmail.com",
        "contactNo": "0123456788",
        "dob": "2023-10-10",
        "address" : {
            "unitNo" : "12",
            "address" : "Jalan Api-api, Kepong 52100 KL"
        }
    }

* #### Response
  * #### Success
        Code : 201 Created
        Content : 
            {
                "id": 3,
                "name": "Ken",
                "email": "ken@gmail.com",
                "contactNo": "0123456788",
                "dob": "2023-10-10"
            }
  * #### Error
        Code : 400 Bad Request
        Content : 
            {
                "name": "Customer name cannot be blank",
                "email": "Invalid email address"
            }

### iii. Update customer

* #### Request
`PUT /api/v1/customer/{customerId}`

* #### Data Param
    URL param :

    `name=[string]`

    `email=[string]`

  * #### Response
    * #### Success
          Code : 200 Success
          Content : 
            {
                "id": 1,
                "name": "Jojo",
                "email": "jojo@hotmail.com",
                "contactNo": "0123456788",
                "dob": "2023-10-10"
            }
    * #### Error
          Code : 400 Bad Request
          Content : 
            {
              "Error Input": "Email had been registered"
            }
          
          OR
    
          Code : 500 Internal Server Error
          Content : 
            {
              "Error Message": "Customer with ID 200 does not exist"
            }

### iv. Delete customer

* #### Request
`DELETE /api/v1/customer/{customerId}`

* #### Response
  * #### Success
        Code : 200 Success
        Content : 
            {}
  * #### Error
        Code : 500 Internal Server Error
        Content : 
          {
            "Error Message": "Customer with ID 200 does not exist"
          }

### v. Get customers with pagination

* #### Request
`GET /api/v1/customer/list`

* #### Data Param
  URL param :

  `page=[int]`

* #### Response
  * #### Success
          Code : 200 Success
          Content : 
              {
                  "content": [
                  {
                      "id": 1,
                      "name": "Adam",
                      "email": "adam@gmail.com",
                      "contactNo": "0123456788",
                      "dob": "2023-10-10"
                  },
                  {
                      "id": 2,
                      "name": "Lily",
                      "email": "lily@gmail.com",
                      "contactNo": "0123456788",
                      "dob": "2023-10-10"
                  },
                  {
                      "id": 3,
                      "name": "Sam",
                      "email": "sam@gmail.com",
                      "contactNo": "0123456788",
                      "dob": "2023-10-10"
                  }
                  ],
                  "pageable": {
                      "pageNumber": 0,
                      "pageSize": 10,
                      "sort": {
                          "empty": true,
                          "sorted": false,
                          "unsorted": true
                      },
                  "offset": 0,
                  "paged": true,
                  "unpaged": false
                  },
                  "last": true,
                  "totalPages": 1,
                  "totalElements": 3,
                  "first": true,
                  "size": 10,
                  "number": 0,
                  "sort": {
                      "empty": true,
                      "sorted": false,
                      "unsorted": true
                  },
                  "numberOfElements": 3,
                  "empty": false
              }
  * #### Error
        Code : 400 Bad Request
        Content : 
          {
            "Error Input": "Page number have no data."
          }

### vi. Get movies

* #### Request
`GET /api/v1/movies`

* #### Response
    * #### Success
          Code : 200 Success
    
          Content :
            [
                {
                    "id":1,
                    "title":"The Treasure of the Sierra Madre",
                    "posterURL":"https://m.media-amazon.com/images/M/MV5BOTJlZWMxYzEtMjlkMS00ODE0LThlM2ItMDI3NGQ2YjhmMzkxXkEyXkFqcGdeQXVyMDI2NDg0NQ@@._V1_SX300.jpg",
                    "imdbId":"tt0040897"
                },
                {
                    "id":2,
                    "title":"The Good,the Bad and the Ugly",
                    "posterURL":"https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_SX300.jpg",
                    "imdbId":"tt0060196"
                },
                .......................,
                {
                    "id":66,
                    "title":"Texas Rangers",
                    "posterURL":"https://m.media-amazon.com/images/M/MV5BMTIwOTM3ODk4NV5BMl5BanBnXkFtZTcwNjI3NjYxMQ@@._V1_SX300.jpg",
                    "imdbId":"tt0193560"
                }
            ]

    * #### Error
            Code : 500 Internal Server Error
        
            Content : 
              {
                "Error Message": "Unable to get movie list"
              }

## Logging
This application create logs in file, log.txt whenever Restful API is called.

* Sample:

        2023-10-21T09:30:32.075+08:00  INFO 11656 --- [http-nio-8080-exec-2] c.junfeng.sample.filter.LoggingFilter    : Restful API Logs: METHOD NAME=GET; REQUEST URI=/api/v1/movies; REQUEST BODY=; RESPONSE CODE=200;RESPONSE BODY=[{"id":1,"title":"The Treasure of the Sierra Madre","posterURL":"https://m.media-amazon.com/images/M/MV5BOTJlZWMxYzEtMjlkMS00ODE0LThlM2ItMDI3NGQ2YjhmMzkxXkEyXkFqcGdeQXVyMDI2NDg0NQ@@._V1_SX300.jpg","imdbId":"tt0040897"},{"id":2,"title":"The Good, the Bad and the Ugly","posterURL":"https://m.media-amazon.com/images/M/MV5BOTQ5NDI3MTI4MF5BMl5BanBnXkFtZTgwNDQ4ODE5MDE@._V1_SX300.jpg","imdbId":"tt0060196"},{"id":3,"title":"High Noon","posterURL":"https://m.media-amazon.com/images/M/MV5BOWIzZGUxZmItOThkMS00Y2QxLTg0MTYtMDdhMjRlNTNlYTI3L2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg","imdbId":"tt0044706"},{"id":4,"title":"Rio Bravo","posterURL":"https://m.media-amazon.com/images/M/MV5BZDVhMTk1NjUtYjc0OS00OTE1LTk1NTYtYWMzMDI5OTlmYzU2XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg","imdbId":"tt0053221"},{"id":5,"title":"True Grit","posterURL":"https://m.media-amazon.com/images/M/MV5BMjIxNjAzODQ0N15BMl5BanBnXkFtZTcwODY2MjMyNA@@._V1_SX300.jpg","imdbId":"tt1403865"},{"id":6,"title":"Once Upon a Time in the West","posterURL":"https://m.media-amazon.com/images/M/MV5BZGI5MjBmYzYtMzJhZi00NGI1LTk3MzItYjBjMzcxM2U3MDdiXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg","imdbId":"tt0064116"},{"id":7,"title":"Unforgiven","posterURL":"https://m.media-amazon.com/images/M/MV5BODM3YWY4NmQtN2Y3Ni00OTg0LWFhZGQtZWE3ZWY4MTJlOWU4XkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg","imdbId":"tt0105695"},{"id":8,"title":"The Searchers","posterURL":"https://m.media-amazon.com/images/M/MV5BYWQ3YWJiMDEtMDBhNS00YjY1LTkzNmEtY2U4Njg4MjQ3YWE3XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg","imdbId":"tt0049730"},{"id":9,"title":"The Wild Bunch","posterURL":"https://m.media-amazon.com/images/M/MV5BNGUyYTZmOWItMDJhMi00N2IxLWIyNDMtNjUxM2ZiYmU5YWU1XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg","imdbId":"tt0065214"},{"id":10,"title":"The Ballad of Buster Scruggs","posterURL":"https://m.media-amazon.com/images/M/MV5BYjRkYTI3M2EtZWQ4Ny00OTA2LWFmMTMtY2E4MTEyZmNjOTMxXkEyXkFqcGdeQXVyNDg4NjY5OTQ@._V1_SX300.jpg","imdbId":"tt6412452"},{"id":11,"title":"Giant","posterURL":"https://m.media-amazon.com/images/M/MV5BYWQ3ZmZhMmQtODQyMS00Y2Q0LThlOTAtM2NiMDMyNDdlYmQ0L2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg","imdbId":"tt0049261"},{"id":12,"title":"Johnny Guitar","posterURL":"https://m.media-amazon.com/images/M/MV5BZDYxMTQzNjgtZDJkMC00OGYwLTliYjgtNDA2ZjBiMmUxYmY4XkEyXkFqcGdeQXVyNjc1NTYyMjg@._V1_SX300.jpg","imdbId":"tt0047136"},{"id":13,"title":"Sweetgrass","posterURL":"https://m.media-amazon.com/images/M/MV5BMTc0NDYyMDY1OF5BMl5BanBnXkFtZTcwOTI4MzExMw@@._V1_SX300.jpg","imdbId":"tt1517252"},{"id":14,"title":"The Man Who Shot Liberty Valance","posterURL":"https://m.media-amazon.com/images/M/MV5BMGEyNzhkYzktMGMyZS00YzRiLWJlYjktZjJkOTU5ZDY0ZGI4XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SX300.jpg","imdbId":"tt0056217"},{"id":15,"title":"Blazing Saddles","posterURL":"https://m.media-amazon.com/images/M/MV5BZGZmMWE1MDYtNzAyNC00MDMzLTgzZjQtNTQ5NjYzN2E4MzkzXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SX300.jpg","imdbId":"tt0071230"},{"id":16,"title":"3:10 to Yuma","posterURL":"https://m.media-amazon.com/images/M/MV5BODE0NTcxNTQzNF5BMl5BanBnXkFtZTcwMzczOTIzMw@@._V1_SX300.jpg","imdbId":"tt0381849"},{"id":17,"title":"Django Unchained","posterURL":"https://m.media-amazon.com/images/M/MV5BMjIyNTQ5NjQ1OV5BMl5BanBnXkFtZTcwODg1MDU4OA@@._V1_SX300.jpg","imdbId":"tt1853728"},{"id":18,"title":"Lone Star","posterURL":"https://m.media-amazon.com/images/M/MV5BN2ViYzQ3YTAtZmQ5OS00ZWNkLWFkZWQtYTJiNTI1OWVlMzc0XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg","imdbId":"tt0116905"},{"id":19,"title":"Butch Cassidy and the Sundance Kid","posterURL":"https://m.media-amazon.com/images/M/MV5BMTkyMTM2NDk5Nl5BMl5BanBnXkFtZTgwNzY1NzEyMDE@._V1_SX300.jpg","imdbId":"tt0064115"},{"id":20,"title":"True Grit","posterURL":"https://m.media-amazon.com/images/M/MV5BMjEwNzRlMzUtODIwMS00N2IwLWE1ZTgtODYzNWY0MGJhODY3XkEyXkFqcGdeQXVyMjI4MjA5MzA@._V1_SX300.jpg","imdbId":"tt0065126"},{"id":21,"title":"Bone Tomahawk","posterURL":"https://m.media-amazon.com/images/M/MV5BMzQ0MzE4OTUzMF5BMl5BanBnXkFtZTgwODAyNzI3NjE@._V1_SX300.jpg","imdbId":"tt2494362"},{"id":22,"title":"The Magnificent Seven","posterURL":"https://m.media-amazon.com/images/M/MV5BMzYyNzU0MTM1OF5BMl5BanBnXkFtZTcwMzE1ODE1NA@@._V1_SX300.jpg","imdbId":"tt0054047"},{"id":23,"title":"McCabe & Mrs. Miller","posterURL":"https://m.media-amazon.com/images/M/MV5BMmMxMTk0MmMtY2I0YS00NjcwLTk2NjYtOWFlOTA0M2EzNjgxL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0067411"},{"id":24,"title":"Near Dark","posterURL":"https://m.media-amazon.com/images/M/MV5BODdkYzVhMmQtYmJhMy00MGMxLTk2ZmEtMmE0YTEwMWNhOTI5XkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0093605"},{"id":25,"title":"The Proposition","posterURL":"https://m.media-amazon.com/images/M/MV5BN2M3MGJlZDktMGE1YS00Y2ZkLThiYTMtYWNjMTFjMjg4MTVhXkEyXkFqcGdeQXVyMTAwMzUyOTc@._V1_SX300.jpg","imdbId":"tt0421238"},{"id":26,"title":"The Making of Meek's Cutoff","posterURL":"N/A","imdbId":"tt2304963"},{"id":27,"title":"Dances with Wolves","posterURL":"https://m.media-amazon.com/images/M/MV5BMTY3OTI5NDczN15BMl5BanBnXkFtZTcwNDA0NDY3Mw@@._V1_SX300.jpg","imdbId":"tt0099348"},{"id":28,"title":"The Homesman","posterURL":"https://m.media-amazon.com/images/M/MV5BMTQ4NTkxOTEyMl5BMl5BanBnXkFtZTgwOTI1NzcwMzE@._V1_SX300.jpg","imdbId":"tt2398231"},{"id":29,"title":"The Hateful Eight","posterURL":"https://m.media-amazon.com/images/M/MV5BMjA1MTc1NTg5NV5BMl5BanBnXkFtZTgwOTM2MDEzNzE@._V1_SX300.jpg","imdbId":"tt3460252"},{"id":30,"title":"Open Range","posterURL":"https://m.media-amazon.com/images/M/MV5BMTYyNjA4NTY3N15BMl5BanBnXkFtZTYwNjc0MDM3._V1_SX300.jpg","imdbId":"tt0316356"},{"id":31,"title":"Shanghai Noon","posterURL":"https://m.media-amazon.com/images/M/MV5BMTI0MjE2MzUwOV5BMl5BanBnXkFtZTYwMTk5NjU3._V1_SX300.jpg","imdbId":"tt0184894"},{"id":32,"title":"Appaloosa","posterURL":"https://m.media-amazon.com/images/M/MV5BMTMxMDE5NDIxM15BMl5BanBnXkFtZTcwNjI0MjQ4MQ@@._V1_SX300.jpg","imdbId":"tt0800308"},{"id":33,"title":"The Assassination of Jesse James by the Coward Robert Ford","posterURL":"https://m.media-amazon.com/images/M/MV5BMTY2NDI2MTc2NV5BMl5BanBnXkFtZTcwNjA2NTQzMw@@._V1_SX300.jpg","imdbId":"tt0443680"},{"id":34,"title":"Red Hill","posterURL":"https://m.media-amazon.com/images/M/MV5BMjQyNjA0MjEzNl5BMl5BanBnXkFtZTcwNjkyNDU5Mw@@._V1_SX300.jpg","imdbId":"tt1530983"},{"id":35,"title":"A Journey of the Soul: The Making of 'Hostiles'","posterURL":"N/A","imdbId":"tt8385112"},{"id":36,"title":"In a Valley of Violence","posterURL":"https://m.media-amazon.com/images/M/MV5BMTUxMjExOTA2OF5BMl5BanBnXkFtZTgwMzI3NTgxMDI@._V1_SX300.jpg","imdbId":"tt3608930"},{"id":37,"title":"The Horse Whisperer","posterURL":"https://m.media-amazon.com/images/M/MV5BZTA5NzU4YzItMjhjYy00YjNjLTljM2MtZDk3ZDE5OGVjNTBhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0119314"},{"id":38,"title":"Tombstone","posterURL":"https://m.media-amazon.com/images/M/MV5BODRkYzA4MGItODE2MC00ZjkwLWI2NDEtYzU1NzFiZGU1YzA0XkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg","imdbId":"tt0108358"},{"id":39,"title":"The Magnificent Seven","posterURL":"https://m.media-amazon.com/images/M/MV5BMTUzNTc0NTAyM15BMl5BanBnXkFtZTgwMTk1ODA5OTE@._V1_SX300.jpg","imdbId":"tt2404435"},{"id":40,"title":"Blackthorn","posterURL":"https://m.media-amazon.com/images/M/MV5BMjA1MDA1MDczMV5BMl5BanBnXkFtZTcwMzE0MjU0Ng@@._V1_SX300.jpg","imdbId":"tt1629705"},{"id":41,"title":"The Salvation of Todd Bentley","posterURL":"N/A","imdbId":"tt4574070"},{"id":42,"title":"Dead Man","posterURL":"https://m.media-amazon.com/images/M/MV5BYTJlZmQ1OTAtODQzZi00NGIzLWI1MmEtZGE4NjFlOWRhODIyXkEyXkFqcGdeQXVyNTc1NTQxODI@._V1_SX300.jpg","imdbId":"tt0112817"},{"id":43,"title":"The Rover","posterURL":"https://m.media-amazon.com/images/M/MV5BMjE4MTc1MzMxN15BMl5BanBnXkFtZTgwMzc4NDA4MTE@._V1.._SX89_AL_.jpg_V1_SX300.jpg","imdbId":"tt2345737"},{"id":44,"title":"Maverick","posterURL":"https://m.media-amazon.com/images/M/MV5BY2I1OTY2NmUtMGVlZi00NjNmLThkNTgtMjExMzRhOTM2MDJiXkEyXkFqcGdeQXVyNDk3NzU2MTQ@._V1_SX300.jpg","imdbId":"tt0110478"},{"id":45,"title":"The Claim","posterURL":"https://m.media-amazon.com/images/M/MV5BYmVjMzVhNWQtZDkyYS00N2YzLTk3M2QtMTY2ZDgxZDRjOTAxXkEyXkFqcGdeQXVyMjI4MjA5MzA@._V1_SX300.jpg","imdbId":"tt0218378"},{"id":46,"title":"The Missing","posterURL":"https://m.media-amazon.com/images/M/MV5BYWM1ZmU5NWItZDM0ZC00MTk5LThiMTktZGUxOThjNzI5NTcyL2ltYWdlL2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0338188"},{"id":47,"title":"Heaven's Gate","posterURL":"https://m.media-amazon.com/images/M/MV5BMzE3YjYzMDgtNjEyYS00ZTk4LThhY2QtMDI4Zjg3MTUyN2ZkXkEyXkFqcGdeQXVyNTE1NjY5Mg@@._V1_SX300.jpg","imdbId":"tt0080855"},{"id":48,"title":"Legends of the Fall","posterURL":"https://m.media-amazon.com/images/M/MV5BMTYwMjYxNTAyN15BMl5BanBnXkFtZTgwMTc3MjkyMTE@._V1_SX300.jpg","imdbId":"tt0110322"},{"id":49,"title":"The Quick and the Dead","posterURL":"https://m.media-amazon.com/images/M/MV5BOTI2ZTZmMmItMmM3YS00ZjUwLWJiODMtMmRjMWM4NDE0OWFhXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0114214"},{"id":50,"title":"The Killer Inside Me","posterURL":"https://m.media-amazon.com/images/M/MV5BMTUyOTIwMTU5MV5BMl5BanBnXkFtZTcwNDY5NjA1Mw@@._V1_SX300.jpg","imdbId":"tt0954947"},{"id":51,"title":"Behind the Scenes of 'Seraphim Falls'","posterURL":"N/A","imdbId":"tt1045816"},{"id":52,"title":"Ned Kelly","posterURL":"https://m.media-amazon.com/images/M/MV5BYmVjYzRkYjItODlmZS00OTEzLTliZjItYjMwOGRmMzhmY2RmXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_SX300.jpg","imdbId":"tt0277941"},{"id":53,"title":"Cowboys & Aliens","posterURL":"https://m.media-amazon.com/images/M/MV5BMTM1MzkyNzQ3OV5BMl5BanBnXkFtZTcwMDk1NTg2NQ@@._V1_SX300.jpg","imdbId":"tt0409847"},{"id":54,"title":"Forsaken","posterURL":"https://m.media-amazon.com/images/M/MV5BODE0NTUwNzA3Ml5BMl5BanBnXkFtZTgwOTQ3OTI3NzE@._V1_SX300.jpg","imdbId":"tt5127300"},{"id":55,"title":"Brimstone & Glory","posterURL":"https://m.media-amazon.com/images/M/MV5BMTU5MzE0NDk0MF5BMl5BanBnXkFtZTgwOTgzMzQwNDI@._V1_SX300.jpg","imdbId":"tt5279122"},{"id":56,"title":"A Million Ways to Die in the West","posterURL":"https://m.media-amazon.com/images/M/MV5BYjUwYjJkNWEtMmE5NC00ZTJjLTk2MTEtNTM5MDcxZjYzZmJkXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg","imdbId":"tt2557490"},{"id":57,"title":"The Lone Ranger","posterURL":"https://m.media-amazon.com/images/M/MV5BZjFiMTc2MTAtZDA0My00OGRmLTk5M2ItNTlmYTUwZmU2YmRiXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_SX300.jpg","imdbId":"tt1210819"},{"id":58,"title":"The Alamo","posterURL":"https://m.media-amazon.com/images/M/MV5BMTAzOTQ5Nzg4MzheQTJeQWpwZ15BbWU2MDQwODAxNw@@._V1_SX300.jpg","imdbId":"tt0318974"},{"id":59,"title":"The Warrior's Way","posterURL":"https://m.media-amazon.com/images/M/MV5BOTU1MWI0NmQtMTIwNi00OGEyLTgyNmItYjEwODBlZDIwYTdhXkEyXkFqcGdeQXVyNzA3Mjg3Mzg@._V1_SX300.jpg","imdbId":"tt1032751"},{"id":60,"title":"The Dark Tower","posterURL":"https://m.media-amazon.com/images/M/MV5BMTU3MjUwMzQ3MF5BMl5BanBnXkFtZTgwMjcwNjkxMjI@._V1_SX300.jpg","imdbId":"tt1648190"},{"id":61,"title":"Wild Wild West","posterURL":"https://m.media-amazon.com/images/M/MV5BYmYyMTc4YjItMGNhNC00OWQwLWJhMWUtNTdjZDgxMDI5MjE2L2ltYWdlXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg","imdbId":"tt0120891"},{"id":62,"title":"Priest","posterURL":"https://m.media-amazon.com/images/M/MV5BMTQ1MTAwODc3OV5BMl5BanBnXkFtZTcwNzI0MDQ3NA@@._V1_SX300.jpg","imdbId":"tt0822847"},{"id":63,"title":"American Outlaws","posterURL":"https://m.media-amazon.com/images/M/MV5BMTIwMTYxODAzMV5BMl5BanBnXkFtZTYwNTM3Nzc5._V1_SX300.jpg","imdbId":"tt0244000"},{"id":64,"title":"September Dawn","posterURL":"https://m.media-amazon.com/images/M/MV5BMTk5MzE0NzM5MV5BMl5BanBnXkFtZTcwODM0NDM0MQ@@._V1_SX300.jpg","imdbId":"tt0473700"},{"id":65,"title":"Jonah Hex","posterURL":"https://m.media-amazon.com/images/M/MV5BMTQ2NzYxNjQyMF5BMl5BanBnXkFtZTcwOTQ0OTI0Mw@@._V1_SX300.jpg","imdbId":"tt1075747"},{"id":66,"title":"Texas Rangers","posterURL":"https://m.media-amazon.com/images/M/MV5BMTIwOTM3ODk4NV5BMl5BanBnXkFtZTcwNjI3NjYxMQ@@._V1_SX300.jpg","imdbId":"tt0193560"}]; TIME TAKEN=1222
        2023-10-21T10:17:51.928+08:00  INFO 11656 --- [http-nio-8080-exec-5] c.junfeng.sample.filter.LoggingFilter    : Restful API Logs: METHOD NAME=GET; REQUEST URI=/api/v1/customer; REQUEST BODY=; RESPONSE CODE=200;RESPONSE BODY=[{"id":1,"name":"Adam","email":"adam@gmail.com","contactNo":"0123456788","dob":"2023-10-10"},{"id":2,"name":"Kenny","email":"kenny@gmail.com","contactNo":"0123456788","dob":"2023-10-10"},{"id":3,"name":"Sam","email":"sam@gmail.com","contactNo":"0123456788","dob":"2023-10-10"}]; TIME TAKEN=448
        2023-10-21T10:17:54.524+08:00  INFO 11656 --- [http-nio-8080-exec-6] c.junfeng.sample.filter.LoggingFilter    : Restful API Logs: METHOD NAME=GET; REQUEST URI=/api/v1/customer/list; REQUEST BODY=; RESPONSE CODE=200;RESPONSE BODY={"content":[{"id":1,"name":"Adam","email":"adam@gmail.com","contactNo":"0123456788","dob":"2023-10-10"},{"id":2,"name":"Kenny","email":"kenny@gmail.com","contactNo":"0123456788","dob":"2023-10-10"},{"id":3,"name":"Sam","email":"sam@gmail.com","contactNo":"0123456788","dob":"2023-10-10"}],"pageable":{"pageNumber":0,"pageSize":10,"sort":{"empty":true,"unsorted":true,"sorted":false},"offset":0,"unpaged":false,"paged":true},"last":true,"totalElements":3,"totalPages":1,"first":true,"numberOfElements":3,"size":10,"number":0,"sort":{"empty":true,"unsorted":true,"sorted":false},"empty":false}; TIME TAKEN=97
        2023-10-21T10:17:58.011+08:00  INFO 11656 --- [http-nio-8080-exec-7] c.junfeng.sample.filter.LoggingFilter    : Restful API Logs: METHOD NAME=POST; REQUEST URI=/api/v1/customer; REQUEST BODY={
        "name": "Nat",
        "email": "nat@gmail.com",
        "contactNo": "0123456788",
        "dob": "2023-10-10",
        "address" : {
        "unitNo" : "12",
        "address" : "Jalan Api-api, Kepong 52100 KL"
        }
        }; RESPONSE CODE=201;RESPONSE BODY={"id":52,"name":"Nat","email":"nat@gmail.com","contactNo":"0123456788","dob":"2023-10-10"}; TIME TAKEN=277
        2023-10-21T10:18:07.556+08:00  INFO 11656 --- [http-nio-8080-exec-8] c.junfeng.sample.filter.LoggingFilter    : Restful API Logs: METHOD NAME=PUT; REQUEST URI=/api/v1/customer/2; REQUEST BODY=; RESPONSE CODE=200;RESPONSE BODY={"id":2,"name":"Kenny","email":"kenny@gmail.com","contactNo":"0123456788","dob":"2023-10-10"}; TIME TAKEN=65
        
