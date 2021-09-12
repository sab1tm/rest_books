package kz.sab1tm.rest_books_server.models.author;

import kz.sab1tm.rest_books_server.models.book.Book;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sabit Murzaliev on 11.09.2021 12:01
 */

public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String age;
    private String sex;

    private List<Book> books = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getAge() {
        if (getBirthDate() != null) {
            LocalDate birthDate = getBirthDate().toLocalDate();
            LocalDate current = LocalDate.now();
            int age = current.getYear() - birthDate.getYear();
            return age + " лет";
        }
        return "<не указана дата рождения>";
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id) &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(birthDate, author.birthDate) &&
                Objects.equals(age, author.age) &&
                Objects.equals(sex, author.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthDate, age, sex);
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", books=" + books +
                '}';
    }
}
