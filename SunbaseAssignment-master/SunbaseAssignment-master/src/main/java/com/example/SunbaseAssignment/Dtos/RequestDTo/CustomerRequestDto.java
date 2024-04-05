package com.example.SunbaseAssignment.Dtos.RequestDTo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomerRequestDto {
    private String first_name;
    private String last_name;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private int    phone;
}
