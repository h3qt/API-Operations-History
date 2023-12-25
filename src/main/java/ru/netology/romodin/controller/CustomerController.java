package ru.netology.romodin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.romodin.domain.Customer;
import ru.netology.romodin.domain.DTO.CustomerDTO;
import ru.netology.romodin.service.CustomerService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO postCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @GetMapping
    public Iterable<CustomerDTO> getCustomers() {
        return customerService.getCustomers()
                .stream()
                .map(customer -> new CustomerDTO(customer.getId(), customer.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomers()
                .stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .map(customer -> new ResponseEntity<>(new CustomerDTO(customer.getId(), customer.getName()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}