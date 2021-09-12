package kz.sab1tm.rest_books_server.controllers;

import kz.sab1tm.rest_books_server.services.BooksAuthorsService;
import kz.sab1tm.rest_books_server.services.RabbitMQService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sabit Murzaliev on 11.09.2021 12:10
 */

@RestController
@RequestMapping("/books-authors")
public class BooksAuthorsController {

    private final BooksAuthorsService service;
    private final RabbitMQService rabbitMQService;

    public BooksAuthorsController(BooksAuthorsService service,
                                  RabbitMQService rabbitMQService) {
        this.service = service;
        this.rabbitMQService = rabbitMQService;
    }

    @GetMapping("/{bookId}/{authorId}")
    public ResponseEntity<Void> find(@RequestHeader(value = "username", required = false) String username,
                                     @PathVariable Long bookId,
                                     @PathVariable Long authorId) {
        Integer result = service.find(bookId, authorId);
        if (result > 0) {
            rabbitMQService.send(username, "GET", "/books-authors/{bookId}/{authorId}",
                    "bookId=" + bookId + ", " + "authorId=" + authorId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{bookId}/{authorId}")
    public ResponseEntity<Void> insert(@RequestHeader(value = "username", required = false) String username,
                                       @PathVariable Long bookId,
                                       @PathVariable Long authorId) {
        Integer result = service.insert(bookId, authorId);
        if (result > 0) {
            rabbitMQService.send(username, "POST", "/books-authors/{bookId}/{authorId}",
                    "bookId=" + bookId + ", " + "authorId=" + authorId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{bookId}/{authorId}")
    public ResponseEntity<Void> delete(@RequestHeader(value = "username", required = false) String username,
                                       @PathVariable Long bookId,
                                       @PathVariable Long authorId) {
        Integer result = service.delete(bookId, authorId);
        if (result > 0) {
            rabbitMQService.send(username, "DELETE", "/books-authors/{bookId}/{authorId}",
                    "bookId=" + bookId + ", " + "authorId=" + authorId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
