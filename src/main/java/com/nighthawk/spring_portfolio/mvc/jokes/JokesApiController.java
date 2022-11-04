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
            Jokes input = new Jokes(null, "joke","d", "imgg", "id", "0");
            repository.save(input);
            return new ResponseEntity<>(input, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
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
            joke.setBoohoo(joke.getBoohoo()+1);
            repository.save(joke);
            return new ResponseEntity<>(joke, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /* Add a new entry
    //  */
    // @RequestMapping(value="/params", method = RequestMethod.GET)
    // public ResponseEntity getParams(@RequestParam Map<String, String> params ) {

    //     System.out.println(params.keySet());
    //     System.out.println(params.values());

    //     return new ResponseEntity<String>("ok", HttpStatus.OK);
    // }

    /* Add a new entry
     */
    @RequestMapping(method=RequestMethod.PUT,value="/newJoke/{jokeStr}/{anthorStr}/{img}")
    public ResponseEntity<Jokes> getNewJoke(@PathVariable String jokeStr,@PathVariable String anthorStr, @PathVariable String img) {

        // return ResponseEntity.ok( questionBankService.getSpecificquestions(subject,topic));
        Jokes input = new Jokes(null, jokeStr, anthorStr, img, "id", "0");
        repository.save(input);
        return new ResponseEntity<>(input, HttpStatus.OK);  // OK HTTP response: status code, headers, and body

    }
}
