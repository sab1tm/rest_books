package kz.sab1tm.rest_books_server.controllers;

import kz.sab1tm.rest_books_server.models.author.Author;
import kz.sab1tm.rest_books_server.services.AuthorService;
import kz.sab1tm.rest_books_server.services.RabbitMQService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 11.09.2021 11:10
 */

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;
    private final RabbitMQService rabbitMQService;

    public AuthorController(AuthorService service,
                            RabbitMQService rabbitMQService) {
        this.service = service;
        this.rabbitMQService = rabbitMQService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getBookList(@RequestHeader(value = "username", required = false) String username) {
        Optional<List<Author>> result = service.getAll();
        if (result.isPresent()) {
            rabbitMQService.send(username, "GET", "/author", null);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getBookById(@RequestHeader(value = "username", required = false) String username,
                                              @PathVariable Long id) {
        Optional<Author> result = service.getById(id);
        if (result.isPresent()) {
            rabbitMQService.send(username, "GET", "/author/{id}", "id=" + id);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Author> create(@RequestHeader(value = "username", required = false) String username,
                                         @RequestBody Author newAuthor) {
        Optional<Author> result = service.create(newAuthor);
        if (result.isPresent()) {
            rabbitMQService.send(username, "POST", "/author", null);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> update(@RequestHeader(value = "username", required = false) String username,
                                         @RequestBody Author newAuthor,
                                         @PathVariable Long id) {
        newAuthor.setId(id);
        Integer result = service.update(newAuthor);
        if (result > 0) {
            Optional<Author> updateAuthor = service.getById(id);
            if (updateAuthor.isPresent()) {
                rabbitMQService.send(username, "PUT", "/author/{id}", "id=" + id);
                return new ResponseEntity<>(updateAuthor.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(value = "username", required = false) String username,
                                       @PathVariable Long id) {
        Integer result = service.delete(id);
        if (result > 0) {
            rabbitMQService.send(username, "DELETE", "/author/{id}", "id="+ id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
