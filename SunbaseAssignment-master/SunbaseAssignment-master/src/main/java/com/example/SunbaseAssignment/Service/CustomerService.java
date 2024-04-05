package com.example.SunbaseAssignment.Service;

import com.example.SunbaseAssignment.Dtos.RequestDTo.CustomerRequestDto;
import com.example.SunbaseAssignment.Models.Customer;

import java.util.List;

public interface CustomerService {
    String create(CustomerRequestDto customerRequestDto) throws Exception;

    String update(CustomerRequestDto customerRequestDto)throws Exception;

    List<Customer> getCustomerList() throws Exception;

    Customer getCustomer(Integer id) throws Exception;

    String deleteCustomer(Integer id)throws Exception;
}
