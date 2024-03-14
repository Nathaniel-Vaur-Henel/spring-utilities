# Spring Utilities - Data - Example

This module is just an example how to use feature of [spring-utilities-data](../../libraries/spring-utilities-data).

## Run the server

Running [ExampleApplication.java](src/main/java/fr/nvh/spring/utilities/ExampleApplication.java) will run a server on8080 port.

### Get persons

You can send request at http://localhost:8080/api/v1/persons, like:

- http://localhost:8080/api/v1/persons?filtre=theshire
    ```json
    [
        {
            "id": 6,
            "firstName": "Frodo",
            "lastName": "Baggins",
            "email": "frodo.baggins@theshire.ring",
            "age": 50
        },
        {
            "id": 7,
            "firstName": "Samwise",
            "lastName": "Gamegee",
            "email": "samwise.gamegee@theshire.ring",
            "age": 38
        },
        {
            "id": 8,
            "firstName": "Meriadoc",
            "lastName": "Brandybuck",
            "email": "meriadoc.brandybuck@theshire.ring",
            "age": 37
        },
        {
            "id": 9,
            "firstName": "Peregrin",
            "lastName": "Took",
            "email": "peregrin.took@theshire.ring",
            "age": 28
        }
    ]
    ```
- http://localhost:8080/api/v1/persons?lastname=Took
    ```json
    [
        {
            "id": 9,
            "firstName": "Peregrin",
            "lastName": "Took",
            "email": "peregrin.took@theshire.ring",
            "age": 28
        }
    ]    
    ```
- http://localhost:8080/api/v1/persons?min_age=100&max_age=200
    ```json
    [
        {
            "id": 5,
            "firstName": "Gimli",
            "lastName": null,
            "email": "gimli@erebor.ring",
            "age": 139
        }
    ]
    ```
- http://localhost:8080/api/v1/persons?min_age=1000
    ```json
    [
        {
            "id": 1,
            "firstName": "Gandalf",
            "lastName": "The Grey",
            "email": "gandalf@valinor.ring",
            "age": 2000
        },
        {
            "id": 4,
            "firstName": "Legolas",
            "lastName": null,
            "email": "legolas@woodlandrealm.ring",
            "age": 2931
        }
    ]
    ```

### Get items

You can send request at http://localhost:8080/api/v1/items, like:

- http://localhost:8080/api/v1/items?owner.filter=theshire
  ```json
  [
    {
      "id": 11,
      "name": "One ring",
      "owner": {
        "id": 6,
        "firstName": "Frodo",
        "lastName": "Baggins",
        "email": "frodo.baggins@theshire.ring",
        "age": 50
      }
    },
    {
      "id": 12,
      "name": "Sting",
      "owner": {
        "id": 6,
        "firstName": "Frodo",
        "lastName": "Baggins",
        "email": "frodo.baggins@theshire.ring",
        "age": 50
      }
    },
    {
      "id": 13,
      "name": "Sam`s bag",
      "owner": {
        "id": 7,
        "firstName": "Samwise",
        "lastName": "Gamegee",
        "email": "samwise.gamegee@theshire.ring",
        "age": 38
      }
    },
    {
      "id": 14,
      "name": "Mr. Bilbo`s book",
      "owner": {
        "id": 7,
        "firstName": "Samwise",
        "lastName": "Gamegee",
        "email": "samwise.gamegee@theshire.ring",
        "age": 38
      }
    },
    {
      "id": 15,
      "name": "Dagger",
      "owner": {
        "id": 8,
        "firstName": "Meriadoc",
        "lastName": "Brandybuck",
        "email": "meriadoc.brandybuck@theshire.ring",
        "age": 37
      }
    },
    {
      "id": 16,
      "name": "Mushrooms",
      "owner": {
        "id": 8,
        "firstName": "Meriadoc",
        "lastName": "Brandybuck",
        "email": "meriadoc.brandybuck@theshire.ring",
        "age": 37
      }
    },
    {
      "id": 17,
      "name": "Dagger",
      "owner": {
        "id": 9,
        "firstName": "Peregrin",
        "lastName": "Took",
        "email": "peregrin.took@theshire.ring",
        "age": 28
      }
    },
    {
      "id": 18,
      "name": "Bacon",
      "owner": {
        "id": 9,
        "firstName": "Peregrin",
        "lastName": "Took",
        "email": "peregrin.took@theshire.ring",
        "age": 28
      }
    }
  ]  
  ```
- http://localhost:8080/api/v1/items?name=Dagger
  ```json
  [
    {
      "id": 15,
      "name": "Dagger",
      "owner": {
        "id": 8,
        "firstName": "Meriadoc",
        "lastName": "Brandybuck",
        "email": "meriadoc.brandybuck@theshire.ring",
        "age": 37
      }
    },
    {
      "id": 17,
      "name": "Dagger",
      "owner": {
        "id": 9,
        "firstName": "Peregrin",
        "lastName": "Took",
        "email": "peregrin.took@theshire.ring",
        "age": 28
      }
    }
  ]
  ```