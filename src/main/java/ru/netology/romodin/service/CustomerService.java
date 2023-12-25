package ru.netology.romodin.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import ru.netology.romodin.domain.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    private final List<Customer> storage = new ArrayList<>();

    public List<Customer> getCustomers() {
        return storage;
    }

    public Customer getCustomer(int index) {
        return storage.get(index);
    }

    public void addCustomer(Customer customer) {
        storage.add(customer);
    }

    @PostConstruct
    public void initStorage() {
        addCustomer(new Customer(1, "Spring"));
        addCustomer(new Customer(2, "Boot"));
    }
}