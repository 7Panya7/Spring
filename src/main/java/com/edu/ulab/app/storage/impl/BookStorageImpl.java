package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.BookStorage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookStorageImpl implements BookStorage {

    private final static Map<Long, Book> bookList = new HashMap<>();

    public void addBookToStorage(Book book) {
        bookList.put(book.getId(),book);
    }

    public Book findBookById(Long id) {
        if (id != null && id > 0 && bookList.containsKey(id)){
            return bookList.get(id);
        }else throw new NotFoundException("Book not found");
    }

    public Book updateBook(Long id, Long pageCount, Long userId, String title, String author) {
        Book book = findBookById(id);
        book.setPageCount(pageCount);
        book.setUserId(userId);
        book.setTitle(title);
        book.setAuthor(author);
        return book;
    }

    public void deleteBookById(Long id) {
        if (id != null && id > 0 && bookList.containsKey(id)){
            bookList.remove(id);
        }else throw new NotFoundException("Book not found");
    }
}
