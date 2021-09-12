package kz.sab1tm.rest_books_server.repositories;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;
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
public class BookRepository {

    String resource = "ibatis/sql-maps-config.xml";
    Reader reader;
    SqlMapClient sqlmap;

    public BookRepository() {
        try {
            reader = Resources.getResourceAsReader(resource);
            sqlmap = SqlMapClientBuilder.buildSqlMapClient(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<List<Book>> findAll() {
        try {
            List<Book> books = (List<Book>) sqlmap.queryForList("Book.findAll");
            if (books != null)
                return Optional.of(books);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> findById(Long id) {
        try {
            Book book = (Book) sqlmap.queryForObject("Book.findById", id);
            if (book != null)
                return Optional.of(book);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Optional<Book> create(Book newBook) {
        try {
            Long newBookId = (Long) sqlmap.insert("Book.insert", newBook);
            if (newBookId != null)
                return findById(newBookId);
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public Integer update(Book newBook) {
        Integer result = 0;
        try {
            result = sqlmap.update("Book.update", newBook);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Integer delete(Long id) {
        Integer result = 0;
        try {
            result = sqlmap.delete("Book.delete", id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}