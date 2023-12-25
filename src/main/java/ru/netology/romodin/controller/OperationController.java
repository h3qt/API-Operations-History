package ru.netology.romodin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.romodin.domain.Operation;
import ru.netology.romodin.service.AsyncInputOperationService;
import ru.netology.romodin.service.StatementService;

import java.util.List;

@RestController
@RequestMapping("/api/operations")
@RequiredArgsConstructor
public class OperationController {
    private final AsyncInputOperationService asyncInputOperationService;
    private final StatementService statementService;

    @PostMapping
    public Operation postOperation(@RequestBody Operation operation) {
        asyncInputOperationService.offerOperation(operation);
        return operation;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Iterable<Operation>> getClientOperations(@PathVariable Integer customerId) {
        List<Operation> operations = statementService.getCustomerOperations(customerId);
        return operations != null && !operations.isEmpty()
                ? new ResponseEntity<>(operations, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Operation> deleteOperation(@PathVariable Integer id) {
        return statementService.getStatement().values().stream()
                .flatMap(List::stream)
                .filter(operation -> operation.getId().equals(id))
                .findFirst()
                .map(operation -> new ResponseEntity<>(operation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}