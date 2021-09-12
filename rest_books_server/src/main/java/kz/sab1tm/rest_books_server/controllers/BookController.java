package kz.sab1tm.rest_books_server.controllers;

import kz.sab1tm.rest_books_server.models.book.Book;
import kz.sab1tm.rest_books_server.services.BookService;
import kz.sab1tm.rest_books_server.services.RabbitMQService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 11.09.2021 10:10
 */

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    private final RabbitMQService rabbitMQService;

    public BookController(BookService service,
                          RabbitMQService rabbitMQService) {
        this.service = service;
        this.rabbitMQService = rabbitMQService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getList(@RequestHeader(value = "username", required = false) String username) {
        Optional<List<Book>> result = service.getAll();
        if (result.isPresent()) {
            rabbitMQService.send(username, "GET", "/book", null);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@RequestHeader(value = "username", required = false) String username,
                                        @PathVariable Long id) {
        Optional<Book> result = service.getById(id);
        if (result.isPresent()) {
            rabbitMQService.send(username, "GET", "/book/{id}", "id="+ id);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestHeader(value = "username", required = false) String username,
                                       @RequestBody Book newBook) {
        Optional<Book> result = service.create(newBook);
        if (result.isPresent()) {
            rabbitMQService.send(username, "POST", "/book", null);
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@RequestHeader(value = "username", required = false) String username,
                                       @RequestBody Book newBook,
                                       @PathVariable Long id) {
        newBook.setId(id);
        Integer result = service.update(newBook);
        if (result > 0) {
            Optional<Book> updateBook = service.getById(id);
            if (updateBook.isPresent()) {
                rabbitMQService.send(username, "PUT", "/book/{id}", "id="+ id);
                return new ResponseEntity<>(updateBook.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader(value = "username", required = false) String username,
                                       @PathVariable Long id) {
        Integer result = service.delete(id);
        if (result > 0) {
            rabbitMQService.send(username, "DELETE", "/book/{id}", "id="+ id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}
