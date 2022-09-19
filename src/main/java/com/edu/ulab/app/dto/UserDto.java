package com.edu.ulab.app.dto;

import com.edu.ulab.app.entity.Book;
import com.edu.ulab.app.entity.User;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto {
    private Long id;
    private String fullName;
    private String title;
    private int age;

}
