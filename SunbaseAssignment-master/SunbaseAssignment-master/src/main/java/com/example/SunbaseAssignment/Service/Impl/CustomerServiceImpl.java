package com.example.SunbaseAssignment.Service.Impl;

import com.example.SunbaseAssignment.Dtos.RequestDTo.CustomerRequestDto;
import com.example.SunbaseAssignment.Exceptions.customerNotPresent;
import com.example.SunbaseAssignment.Exceptions.emailAlreadyExist;
import com.example.SunbaseAssignment.Models.Customer;
import com.example.SunbaseAssignment.Repository.CustomerRepository;
import com.example.SunbaseAssignment.Service.CustomerService;
import com.example.SunbaseAssignment.Transformations.CustomerTransformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public String create(CustomerRequestDto customerRequestDto) throws Exception {

        //trasforming data
        Customer customer = CustomerTransformation.convertEntity(customerRequestDto);


        //checking if email is already exist or not
        Optional<Customer> optionalCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
        if(optionalCustomer.isPresent()){
            throw new emailAlreadyExist("this email id is already exist");
        }

        // adding the new customer in database
        customerRepository.save(customer);

        return "New Customer created successfully";
    }

    @Override
    public String update(CustomerRequestDto customerRequestDto) throws Exception {
        return null;
    }

    @Override
    public List<Customer> getCustomerList() throws Exception {

        //finding list of customer
        List<Customer> customerList = customerRepository.findAll();

        //checking if list is null and throwing Exception
        if(customerList.isEmpty()){
            throw  new Exception("There is no Customer");
        }

        //if list is not then return list of customer
        return customerList;

    }

    @Override
    public Customer getCustomer(Integer id) throws Exception {
      //finding customer in database by their id
      Optional<Customer> optionalCustomer = customerRepository.findById(id);

      //checking the id is present in database or not
      if(!optionalCustomer.isPresent()){
          throw new customerNotPresent("this id is not valid or not present in database");
      }


      Customer customer = optionalCustomer.get();

      // Return the customer
      return customer;
    }

    @Override
    public String deleteCustomer(Integer id) throws Exception {

        Optional<Customer> optionalCustomer = customerRepository.findById(id);


        //checking the id is present in database or not
        if(!optionalCustomer.isPresent()){
            throw new customerNotPresent("this id is not valid or not present in database");
        }


        Customer customer = optionalCustomer.get();


        customerRepository.delete(customer);

        return "the customer having"+customer.getId()+"is Deleted";
    }
}
