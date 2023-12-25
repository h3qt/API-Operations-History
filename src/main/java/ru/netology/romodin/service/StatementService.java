package ru.netology.romodin.service;

import org.springframework.stereotype.Service;
import ru.netology.romodin.domain.Operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatementService {

    private final Map<Integer, List<Operation>> storage = new HashMap<>();

    public Map<Integer, List<Operation>> getStatement() {
        return storage;
    }

    public List<Operation> getCustomerOperations(int customerId) {
        return storage.getOrDefault(customerId, List.of());
    }

    public Operation getOperation(int clientId, int operationIndex) {
        return storage.getOrDefault(clientId, List.of()).get(operationIndex);
    }

    public void setOperation(int clientId, Operation operation) {
        storage.computeIfAbsent(clientId, k -> new ArrayList<>())
                .add(operation);
    }
}