package com.example.SunbaseAssignment.Transformations;

import com.example.SunbaseAssignment.Dtos.RequestDTo.CustomerRequestDto;
import com.example.SunbaseAssignment.Models.Customer;

public class CustomerTransformation {

    public static Customer convertEntity (CustomerRequestDto customerRequestDto){
        Customer customer = Customer.builder()
                .first_name(customerRequestDto.getFirst_name())
                .last_name(customerRequestDto.getLast_name())
                .address(customerRequestDto.getAddress())
                .city(customerRequestDto.getCity())
                .state(customerRequestDto.getState())
                .email(customerRequestDto.getEmail())
                .phone(customerRequestDto.getPhone())
                .street(customerRequestDto.getStreet())

                .build();
        return customer;
    }
}
