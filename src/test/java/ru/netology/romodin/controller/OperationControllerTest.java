package ru.netology.romodin.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.netology.romodin.OperationHistoryApiApplicationTest;
import ru.netology.romodin.config.OperationProperties;
import ru.netology.romodin.domain.Currency;
import ru.netology.romodin.domain.Operation;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OperationProperties operationProperties;

    @Test
    public void operationControllerWorksTest() throws Exception {
        Operation operation = new Operation(17, 370, Currency.RUB, "Club", 1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/operations/%d".formatted(operation.getCustomerId())))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/operations")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(operation)));

        Thread.sleep(3L * operationProperties.getSleepMilliSeconds());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/operations/%d".formatted(operation.getCustomerId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(17)))
                .andExpect(jsonPath("$[0].sum", equalTo(370)))
                .andExpect(jsonPath("$[0].currency", equalTo(Currency.RUB.toString())))
                .andExpect(jsonPath("$[0].merchant", equalTo("Club")))
                .andExpect(jsonPath("$[0].customerId", equalTo(1)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/operations/%d".formatted(operation.getId())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.id", equalTo(17)))
                .andExpect(jsonPath("$.sum", equalTo(370)))
                .andExpect(jsonPath("$.merchant", equalTo("Club")))
                .andExpect(jsonPath("$.currency", equalTo(Currency.RUB.toString())))
                .andExpect(jsonPath("$.customerId", equalTo(1)));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/operations/%d".formatted(2)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}