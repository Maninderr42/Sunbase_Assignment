package com.example.SunbaseAssignment.Dtos.RequestDTo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    private String username;
    private String email;
    private String password;
    private String age;
    private String gender;
    //we can add other field as we want
}
