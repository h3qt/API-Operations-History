package ru.netology.romodin;

import ru.netology.romodin.domain.Customer;
import ru.netology.romodin.domain.Operation;
import ru.netology.romodin.service.StorageService;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer();
        Operation operation = new Operation();
        StorageService<Customer> customerStorageService = new StorageService<>(StorageService.MAX_CUSTOMERS);
        StorageService<Operation> operationStorageService = new StorageService<>(StorageService.MAX_OPERATIONS);

        inputCustomer(scanner, customerStorageService);
        inputOperation(scanner, operationStorageService);
    }

    public static void inputOperation(Scanner scanner, StorageService<Operation> storageService) {
        int operationId = 0;
        while (true) {
            System.out.println("Sum: ");
            int sum = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Currency: ");
            String currency = scanner.nextLine();

            System.out.println("Merchant: ");
            String merchant = scanner.nextLine();

            Operation operation = new Operation(operationId, sum, currency, merchant);

            storageService.getOperations()[operationId] = operation;
            operationId++;

            System.out.println("Customer: ");
            int customerID = scanner.nextInt();
            scanner.nextLine();

            int operationsCountForCustomer = storageService.getOperationsPerCustomer();
            storageService.getStatement()[customerID][operationsCountForCustomer] = operationId;

            System.out.println("Do you want to enter the next operation? Y/N");
            String answer = scanner.nextLine();
            if (answer.equals("N")) {
                break;
            }
            if (operationId == storageService.getMaxOperations()) {
                break;
            }
        }
    }
    public static void inputCustomer(Scanner scanner, StorageService<Customer> storageService) {
        int customerId = 0;
        while (true) {
            System.out.println("Customer name: ");
            String name = scanner.nextLine();

            Customer customer = new Customer(customerId, name);

            storageService.getCustomers()[customerId] = customer;
            customerId++;

            System.out.println("Do you want to enter next customer? Y/N");
            String answer = scanner.nextLine();

            if (answer.equals("N")) {
                break;
            }
            if (customerId == storageService.getMaxCustomers()) {
                break;
            }
        }
    }
}