package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.romodin.Main;

import java.util.Scanner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation {
    private int id;
    private int sum;
    private String currency;
    private String merchant;

    public void printToConsole() {
        System.out.println("Operation ID: " + id);
        System.out.println("Amount: " + sum + " " + currency);
        System.out.println("Merchant: " + merchant);
        System.out.println("------------------------");
    }

    public static void inputOperation(Scanner scanner) {
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

            Main.operations[operationId] = operation;
            operationId++;

            System.out.println("Customer: ");
            int customerID = scanner.nextInt();
            scanner.nextLine();

            int operationsCountForCustomer = Main.customer_operations_count[customerID];
            Main.statement[customerID][operationsCountForCustomer] = operationId;
            Main.customer_operations_count[customerID] = operationsCountForCustomer + 1;

            System.out.println("Do you want to enter the next operation? Y/N");
            String answer = scanner.nextLine();
            if (answer.equals("N")) {
                break;
            }
            if (operationId == Main.MAX_OPERATIONS) {
                break;
            }
        }
    }
}