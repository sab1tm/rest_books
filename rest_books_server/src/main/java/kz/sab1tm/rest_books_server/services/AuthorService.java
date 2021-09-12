package kz.sab1tm.rest_books_server.services;

import kz.sab1tm.rest_books_server.models.author.Author;
import kz.sab1tm.rest_books_server.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 11.09.2021 15:09
 */

@Service
public class AuthorService {

    private final AuthorRepository repository;

    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public Optional<List<Author>> getAll() {
        return repository.findAll();
    }

    public Optional<Author> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<Author> create(Author newAuthor) {
        return repository.create(newAuthor);
    }

    public Integer update(Author newAuthor) {
        return repository.update(newAuthor);
    }

    public Integer delete(Long id) {
        return repository.delete(id);
    }

}
