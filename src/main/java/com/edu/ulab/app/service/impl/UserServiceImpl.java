package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.UserMapper;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserStorage storage;
    private final UserMapper mapper;
    private Long id;
    @Override
    public User createUser(UserDto userDto) {
        User user = new User();

        // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id
        user.setId(++id);
        user.setAge(userDto.getAge());
        user.setTitle(userDto.getTitle());
        user.setFullName(userDto.getFullName());
        storage.addUserToStorage(user);
        return user;
    }

    @Override
    public User updateUser(Long id, int age, List<Book> books, String title, String fullNAme) {
        return storage.updateUser(id, age, books, title, fullNAme);
    }

    @Override
    public User getUserById(Long id) {
        return storage.findUserById(id);
    }

    @Override
    public void deleteUserById(Long id) {
        storage.deleteUserById(id);
    }
}
