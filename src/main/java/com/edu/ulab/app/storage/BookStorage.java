package com.edu.ulab.app.storage;

import com.edu.ulab.app.entity.Book;

public interface BookStorage {

    public void addBookToStorage(Book book);

    public Book findBookById(Long id);

    public Book updateBook(Long id, Long pageCount, Long userId, String title, String author);

    public void deleteBookById(Long id);
}
