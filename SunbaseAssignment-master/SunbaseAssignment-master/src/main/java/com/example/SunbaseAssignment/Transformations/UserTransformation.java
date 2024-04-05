package com.example.SunbaseAssignment.Transformations;


import com.example.SunbaseAssignment.Dtos.RequestDTo.UserRequestDto;
import com.example.SunbaseAssignment.Models.User;

public class UserTransformation {

    public static User convertEntity(UserRequestDto userRequestDto){
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .email(userRequestDto.getEmail())
                .password(userRequestDto.getPassword())
                .age(userRequestDto.getAge())
                .gender(userRequestDto.getGender())
                .build();
        return user;
    }
}
