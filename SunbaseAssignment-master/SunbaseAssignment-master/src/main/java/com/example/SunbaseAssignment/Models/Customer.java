package com.example.SunbaseAssignment.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private  String address;

    @Column(nullable = false)
    private String city;
    private String state;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private int phone;


}
