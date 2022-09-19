package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.storage.BookStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookStorage storage;
    private final BookMapper mapper;

    private Long id;
    @Override
    public Book createBook(BookDto bookDto) {
        Book book = new Book();
        book.setId(++id);
        book.setUserId(bookDto.getUserId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPageCount(bookDto.getPageCount());
        return book;
    }

    @Override
    public Book updateBook(Long id, String title, Long pageCount, Long userId, String author) {
        return storage.updateBook(id, pageCount, userId, title, author);
    }

    @Override
    public Book getBookById(Long id) {
        return storage.findBookById(id);

    }

    @Override
    public void deleteBookById(Long id) {
        storage.deleteBookById(id);
    }
}
