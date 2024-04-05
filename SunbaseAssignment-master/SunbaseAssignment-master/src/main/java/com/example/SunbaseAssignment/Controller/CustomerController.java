package com.example.SunbaseAssignment.Controller;

import com.example.SunbaseAssignment.Dtos.RequestDTo.CustomerRequestDto;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer") // Changed the API base path
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // API endpoint for creating a customer
    @PostMapping("/create")
    public ResponseEntity createCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception {
        try {
            String result = customerService.create(customerRequestDto);
            return new ResponseEntity(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API endpoint for updating a customer
    @PutMapping("/update")
    public ResponseEntity updateCustomer(@RequestBody CustomerRequestDto customerRequestDto) throws Exception {
        try {
            String result = customerService.update(customerRequestDto);
            return new ResponseEntity(result, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // API endpoint for getting a list of all customers
    @GetMapping("/list")
    public ResponseEntity getAll() throws Exception {
        try {
            List<Customer> customerList = customerService.getCustomerList();
            return new ResponseEntity(customerList, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // API endpoint for getting a customer by ID
    @GetMapping("/get/{id}")
    public ResponseEntity getCustomer(@PathVariable Integer id) throws Exception {
        try {
            Customer customer = customerService.getCustomer(id);
            return new ResponseEntity(customer, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // API endpoint for deleting a customer by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id) throws Exception {
        try {
            String result = customerService.deleteCustomer(id);
            return new ResponseEntity(result, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
