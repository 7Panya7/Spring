package com.edu.ulab.app.facade;

import com.edu.ulab.app.dto.BookDto;
import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.BookMapper;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.BookService;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.service.impl.BookServiceImpl;
import com.edu.ulab.app.storage.BookStorage;
import com.edu.ulab.app.web.request.UserBookRequest;
import com.edu.ulab.app.web.request.UserRequest;
import com.edu.ulab.app.web.response.UserBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class UserDataFacade {
    @Autowired
    private BookStorage storage;
    private final UserService userService;
    private final BookService bookService;
    private final UserMapper userMapper;
    private final BookMapper bookMapper;


    public UserDataFacade(UserService userService, BookService bookService, UserMapper userMapper, BookMapper bookMapper) {
        this.userService = userService;
        this.bookService = bookService;
        this.userMapper = userMapper;
        this.bookMapper = bookMapper;
    }


    public UserBookResponse createUserWithBooks(UserBookRequest userBookRequest) {
        log.info("Got user book create request: {}", userBookRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userBookRequest.getUserRequest());
        log.info("Mapped user request: {}", userDto);

        User createdUser = userService.createUser(userDto);
        log.info("Created user: {}", createdUser);

        List<Long> bookIdList = userBookRequest.getBookRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(createdUser.getId()))
                .peek(mappedBookDto -> log.info("mapped book: {}", mappedBookDto))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Created book: {}", createdBook))
                .peek(book -> storage.addBookToStorage(book))
                .peek(createdUser::addBook)
                .map(Book::getId)
                .toList();
        log.info("Collected book ids: {}", bookIdList);

        return UserBookResponse.builder()
                .userId(createdUser.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse updateUserWithBooks(Long id, UserBookRequest userBookRequest) {
        log.info("Got user books create request: {}", userBookRequest);
        User userById = userService.getUserById(id);
        log.info("Got user from storage : {}", userById);
        UserRequest userRequest = userBookRequest.getUserRequest();
        log.info("Got user request: {}", userRequest);
        UserDto userDto = userMapper.userRequestToUserDto(userRequest);
        log.info("Got userDto from request: {}", userDto);
        userById.setFullName(userDto.getFullName());
        userById.setTitle(userDto.getTitle());
        userById.setAge(userDto.getAge());
        userById.setBooks(null);
        List<Long> bookIdList = userBookRequest.getBookRequests()
                .stream()
                .filter(Objects::nonNull)
                .map(bookMapper::bookRequestToBookDto)
                .peek(bookDto -> bookDto.setUserId(id))
                .peek(mappedBook -> log.info("mapped book: {}", mappedBook))
                .map(bookService::createBook)
                .peek(createdBook -> log.info("Creat book: {}", createdBook))
                .peek(userById::addBook)
                .map(Book::getId)
                .toList();
        log.info("Updat user : {}", userById);
        return UserBookResponse.builder()
                .userId(userById.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public UserBookResponse getUserWithBooks(Long userId) {
        User userById = userService.getUserById(userId);
        log.info("Got user from storage : {}", userById);
        List<Book> books = userById.getBooks();
        List<Long> bookIdList = books.stream()
                .map(Book::getId)
                .toList();
        return UserBookResponse.builder()
                .userId(userById.getId())
                .booksIdList(bookIdList)
                .build();
    }

    public HttpStatus deleteUserWithBooks(Long userId) {
        userService.deleteUserById(userId);
        log.info("User with id={} deleted from storage", userId);
        return HttpStatus.OK;
    }
}
