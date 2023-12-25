package ru.netology.romodin.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.netology.romodin.OperationHistoryApiApplicationTest;
import ru.netology.romodin.config.OperationProperties;
import ru.netology.romodin.domain.Currency;
import ru.netology.romodin.domain.Operation;

import static org.junit.jupiter.api.Assertions.*;

public class AsyncInputOperationServiceTest extends OperationHistoryApiApplicationTest {
    @Autowired
    private AsyncInputOperationService asyncInputOperationService;
    @Autowired
    private StatementService statementService;
    @Autowired
    private OperationProperties properties;

    @Test
    public void asyncInputOperationServiceWorksTest() throws InterruptedException {
        Operation operation = new Operation(17, 370, Currency.RUB, "Club", 1);

        asyncInputOperationService.offerOperation(operation);
        Thread.sleep(3L * properties.getSleepMilliSeconds());

        Operation operationOfService = statementService.getOperation(operation.getCustomerId(), 0);

        assertEquals(operation, operationOfService);
        assertEquals(operation.getId(), operationOfService.getId());
        assertEquals(operation.getSum(), operationOfService.getSum());
        assertEquals(operation.getCurrency(), operationOfService.getCurrency());
        assertEquals(operation.getMerchant(), operationOfService.getMerchant());
    }
}