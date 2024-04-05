package com.example.SunbaseAssignment.Service;

import com.example.SunbaseAssignment.Dtos.RequestDTo.UserRequestDto;

public interface AuthService {
    String login(String username, String password);

    String  signup(UserRequestDto userRequestDto)throws Exception;
}
