package kz.sab1tm.rest_books_server.services;

import kz.sab1tm.rest_books_server.repositories.BooksAuthorsRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Sabit Murzaliev on 11.09.2021 16:09
 */

@Service
public class BooksAuthorsService {

    private final BooksAuthorsRepository repository;

    public BooksAuthorsService(BooksAuthorsRepository repository) {
        this.repository = repository;
    }

    public Integer find(Long bookId, Long authorId) {
        return repository.find(bookId, authorId);
    }

    public Integer insert(Long bookId, Long authorId) {
        return repository.insert(bookId, authorId);
    }

    public Integer delete(Long bookId, Long authorId) {
        return repository.delete(bookId, authorId);
    }

}
