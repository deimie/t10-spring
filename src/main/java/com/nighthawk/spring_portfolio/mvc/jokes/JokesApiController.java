package com.nighthawk.spring_portfolio.mvc.jokes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/jokes")  // all requests in file begin with this URI
public class JokesApiController {

    // Autowired enables Control to connect URI request and POJO Object to easily for Database CRUD operations
    @Autowired
    private JokesJpaRepository repository;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<Jokes>> getJokes() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/like/{id}")
    public ResponseEntity<Jokes> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        

        Optional<Jokes> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID

            repository.save(new Jokes(null, "hi","user","user"));
           // return new ResponseEntity<>(input, HttpStatus.OK);
        

         /* Jokes joke = optional.get();  // value from findByID
            joke.setUsername(joke.getUsername()); // increment value
            repository.save(joke);  // save entity
              */ // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /* Update Jeer
     */
    @PutMapping("/jeer/{id}")
    public ResponseEntity<Jokes> setJeer(@PathVariable long id) {
        Optional<Jokes> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Jokes joke = optional.get();
            joke.setPassword(joke.getPassword());
            repository.save(joke);
            return new ResponseEntity<>(joke, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}