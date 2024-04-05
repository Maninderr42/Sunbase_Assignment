package com.example.SunbaseAssignment.Controller;

import com.example.SunbaseAssignment.Dtos.RequestDTo.AuthRequestDto;
import com.example.SunbaseAssignment.Dtos.RequestDTo.UserRequestDto;
import com.example.SunbaseAssignment.Dtos.ResponseDto.AuthResponseDto;
import com.example.SunbaseAssignment.Enum.AuthStatus;
import com.example.SunbaseAssignment.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authiction")
public class authController {

    @Autowired
    private AuthService authService;

    // Endpoint for user login
    @PostMapping("/loginPage")
    public ResponseEntity loginPage(@RequestBody AuthRequestDto authRequestDto){
        // Call login method from the AuthService
        var jwtToken = authService.login(authRequestDto.getUsername(), authRequestDto.getPassword());
        // Create response DTO with the JWT token and login success status
        var Result = new AuthResponseDto(jwtToken, AuthStatus.LOGIN_SUCCESS);
        // Return response entity with OK status and response body
        return ResponseEntity.status(HttpStatus.OK).body(Result);
    }

    // Endpoint for user signup
    @PostMapping("/signUpPage")
    public ResponseEntity SignupPage(@RequestBody UserRequestDto userRequestDto) {
        try {
            // Call signup method from the AuthService
            var jwtToken = authService.signup(userRequestDto);
            // Create response DTO with the JWT token and user created success status
            var Result = new AuthResponseDto(jwtToken, AuthStatus.USER_CREATED_SUCCESSFULLY);
            // Return response entity with OK status and response body
            return ResponseEntity.status(HttpStatus.OK).body(Result);
        } catch (Exception e) {
            // If an exception occurs during signup, return conflict status with user not created status
            var Result = new AuthResponseDto(null, AuthStatus.USER_NOT_CREATED);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Result);
        }
    }
}
