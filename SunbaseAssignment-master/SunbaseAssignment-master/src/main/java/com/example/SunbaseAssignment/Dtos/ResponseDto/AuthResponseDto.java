package com.example.SunbaseAssignment.Dtos.ResponseDto;


import com.example.SunbaseAssignment.Enum.AuthStatus;

public record AuthResponseDto(String token, AuthStatus authStatus){
}
