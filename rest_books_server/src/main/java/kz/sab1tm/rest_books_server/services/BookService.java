package kz.sab1tm.rest_books_server.services;

import kz.sab1tm.rest_books_server.models.book.Book;
import kz.sab1tm.rest_books_server.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 11.09.2021 15:29
 */

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Book>> getAll() {
        return repository.findAll();
    }

    public Optional<Book> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Book> create(Book newBook) {
        return repository.create(newBook);
    }

    public Integer update(Book newBook) {
        return repository.update(newBook);
    }

    public Integer delete(Long id) {
        return repository.delete(id);
    }

}
