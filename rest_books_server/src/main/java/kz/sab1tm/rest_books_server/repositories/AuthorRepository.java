package kz.sab1tm.rest_books_server.repositories;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
import kz.sab1tm.rest_books_server.models.author.Author;
import kz.sab1tm.rest_books_server.models.book.Book;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

/**
 * Created by Sabit Murzaliev on 11.09.2021 14:01
 */

@Repository
public class AuthorRepository {

    String resource = "ibatis/sql-maps-config.xml";
    Reader reader;
    SqlMapClient sqlmap;

    public AuthorRepository() {
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlmap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<Author>> findAll() {
        try {
            List<Author> authors = (List<Author>) sqlmap.queryForList("Author.findAll");
            if (authors != null)
                return Optional.of(authors);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Author> findById(Long id) {
        try {
            Author author = (Author) sqlmap.queryForObject("Author.findById", id);
            if (author != null)
                return Optional.of(author);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Author> create(Author newAuthor) {
        try {
            Long newAuthorId = (Long) sqlmap.insert("Author.insert", newAuthor);
            if (newAuthorId != null)
                return findById(newAuthorId);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Integer update(Author newAuthor) {
        Integer result = 0;
        try {
            result = sqlmap.update("Author.update", newAuthor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer delete(Long id) {
        Integer result = 0;
        try {
            result = sqlmap.delete("Author.delete", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}