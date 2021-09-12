package kz.sab1tm.rest_books_server.models.book;

import kz.sab1tm.rest_books_server.models.author.Author;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sabit Murzaliev on 11.09.2021 12:08
 */

public class Book {

    private Long id;
    private String name;
    private String published;

    private List<Author> authors = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublished() {
        return published;
    }

    public void setPublished(String published) {
        this.published = published;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(published, book.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, published);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", published='" + published + '\'' +
                ", authors=" + authors +
                '}';
    }
}
