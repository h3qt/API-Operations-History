package ru.netology.romodin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.netology.romodin.OperationHistoryApiApplicationTest;
import ru.netology.romodin.domain.Customer;
import ru.netology.romodin.domain.DTO.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void customerControllerWorksTest() throws Exception {
        List<CustomerDTO> customersDto = new ArrayList<>(List.of(
                new CustomerDTO(1, "Spring"), new CustomerDTO(2, "Boot")
        ));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Spring", "Boot")));


        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Spring")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("Boot")));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/23"))
                .andExpect(status().isNotFound());

        Customer customer = new Customer(17, "Caesar");
        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(customer.getId())))
                .andExpect(jsonPath("$.name", equalTo(customer.getName())));

        customersDto.add(new CustomerDTO(customer.getId(), customer.getName()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2, customer.getId())))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("Spring", "Boot", customer.getName())));
    }
}