package com.edu.ulab.app.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class User {
    private Long id;
    private String fullName;
    private String title;
    private List<Book> books;
    private int age;

    public void addBook(Book book) {
        if (this.books == null) {
            this.books = new ArrayList<>();
            this.books.add(book);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(id, user.id) && Objects.equals(fullName, user.fullName) && Objects.equals(title, user.title) && Objects.equals(books, user.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, title, books, age);
    }

}
