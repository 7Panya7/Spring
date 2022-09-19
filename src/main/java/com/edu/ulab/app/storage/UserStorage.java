package com.edu.ulab.app.storage;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;

import java.util.List;

public interface UserStorage {

    public void addUserToStorage(User user);

    public User findUserById(Long id);

    public User updateUser(Long id, int age, List<Book> books, String title, String fullName);

    public void deleteUserById(Long id);
}
