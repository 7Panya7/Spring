package com.edu.ulab.app.service;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;

import java.util.List;

public interface UserService {
    User createUser(UserDto userDto);

    User updateUser(Long id, int age, List<Book> books, String title, String fullNAme);

    User getUserById(Long id);

    void deleteUserById(Long id);
}
