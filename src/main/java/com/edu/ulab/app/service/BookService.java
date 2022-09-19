package com.edu.ulab.app.service;


import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import org.springframework.stereotype.Service;

import java.util.function.Function;

public interface BookService {
    Book createBook(BookDto dto);

    Book updateBook(Long id, String title, Long pageCount, Long userId, String author);

    Book getBookById(Long id);

    void deleteBookById(Long id);
}
