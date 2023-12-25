package ru.netology.romodin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.romodin.OperationHistoryApiApplicationTest;
import ru.netology.romodin.domain.Currency;
import ru.netology.romodin.domain.Operation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatementServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private StatementService statementService;

    @Test
    public void saveInStatementServiceTest() {
        int operationId = 1;
        int operationSum = 370;
        Currency operationCurrency = Currency.RUB;
        String operationMerchant = "Club";
        int customerId = 17;

        Operation operation = new Operation(operationId, operationSum, operationCurrency, operationMerchant, customerId);
        statementService.setOperation(customerId, operation);
        Operation operationOfService = statementService.getOperation(customerId, 0);

        assertEquals(operation, operationOfService);
        assertEquals(operationId, operationOfService.getId());
        assertEquals(operationSum, operationOfService.getSum());
        assertEquals(operationCurrency, operationOfService.getCurrency());
        assertEquals(operationMerchant, operationOfService.getMerchant());
        assertEquals(customerId, operationOfService.getCustomerId());
    }
}