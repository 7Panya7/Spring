package com.edu.ulab.app.storage.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.exception.NotFoundException;
import com.edu.ulab.app.storage.UserStorage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserStorageImpl implements UserStorage {

    private final static Map<Long, User> userList = new HashMap<>();

    public void addUserToStorage(User user) {
        userList.put(user.getId(),user);
    }

    public User findUserById(Long id) {
        if (id != null && id > 0 && userList.containsKey(id)){
            return userList.get(id);
        }else throw new NotFoundException("User not found");
    }

    public User updateUser(Long id, int age, List<Book> books, String title, String fullName) {
        User user = findUserById(id);
        user.setAge(age);
        user.setBooks(books);
        user.setTitle(title);
        user.setFullName(fullName);
        return user;
    }

    public void deleteUserById(Long id) {
        if (id != null && id > 0 && userList.containsKey(id)){
            userList.remove(id);
        }else throw new NotFoundException("User not found");
    }
    //todo создать хранилище в котором будут содержаться данные
    // сделать абстракции через которые можно будет производить операции с хранилищем
    // продумать логику поиска и сохранения
    // продумать возможные ошибки
    // учесть, что при сохранеии юзера или книги, должен генерироваться идентификатор
    // продумать что у узера может быть много книг и нужно создать эту связь
    // так же учесть, что методы хранилища принимают друго тип данных - учесть это в абстракции
}
