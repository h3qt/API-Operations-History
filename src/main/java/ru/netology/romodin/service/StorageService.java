package ru.netology.romodin.service;

import ru.netology.romodin.domain.Customer;
import ru.netology.romodin.domain.Operation;

import java.util.ArrayList;
import java.util.List;

public class StorageService<T> {
    private List<T> storage = new ArrayList<>();
    public final static int MAX_OPERATIONS = 1000;
    public final static int MAX_CUSTOMERS = 100;
    private final static Operation[] operations = new Operation[MAX_OPERATIONS];
    private final static Customer[] customers = new Customer[MAX_CUSTOMERS];
    private final static int OPERATIONS_PER_CUSTOMER = MAX_OPERATIONS/MAX_CUSTOMERS;
    private final static int[][] statement = new int[MAX_CUSTOMERS][OPERATIONS_PER_CUSTOMER];
    StorageService<Customer> customerStorageService = new StorageService<>(MAX_CUSTOMERS);
    StorageService<Operation> operationStorageService = new StorageService<>(MAX_CUSTOMERS * OPERATIONS_PER_CUSTOMER);

    public int getMaxOperations(){ return MAX_OPERATIONS; }
    public int getMaxCustomers(){ return MAX_CUSTOMERS; }
    public int getOperationsPerCustomer(){ return OPERATIONS_PER_CUSTOMER; }
    public Customer[] getCustomers() { return customers; }
    public Operation[] getOperations() {
        return operations;
    }
    public int[][] getStatement() { return statement; }

    public StorageService(int capacity) {
        this.storage = List.of((T[]) new Object[capacity]);
    }

    public StorageService() {

    }

    public T getElement(int position) {
        return storage.get(position);
    }

    public void setElement(T element) {
        storage.add(element);
    }
}
