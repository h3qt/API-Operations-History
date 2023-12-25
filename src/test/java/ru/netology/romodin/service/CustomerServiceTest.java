package ru.netology.romodin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.romodin.OperationHistoryApiApplicationTest;
import ru.netology.romodin.domain.Customer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void getClientsTest() {
        assertEquals(new Customer(1, "Spring"), customerService.getCustomer(0));
        assertEquals(new Customer(2, "Boot"), customerService.getCustomer(1));
    }

    @Test
    public void saveInCustomerServiceTest(){
        int customerId = 17;
        String customerName = "Caesar";
        Customer caesar = new Customer(customerId, customerName);

        customerService.addCustomer(caesar);
        Customer customer = customerService.getCustomer(2);

        assertEquals(caesar, customer);
        assertEquals(customerId, customer.getId());
        assertEquals(customerName, customer.getName());
    }
}