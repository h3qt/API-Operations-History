package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.romodin.Main;

import java.util.Scanner;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private int id;
    private String name;

    public static void inputCustomer(Scanner scanner) {
        int customerId = 0;
        while (true) {
            System.out.println("Customer name: ");
            String name = scanner.nextLine();

            Customer customer = new Customer(customerId, name);

            Main.customers[customerId] = customer;
            customerId++;

            System.out.println("Do you want to enter next customer? Y/N");
            String answer = scanner.nextLine();

            if (answer.equals("N")) {
                break;
            }
            if (customerId == Main.MAX_CUSTOMERS) {
                break;
            }
        }
    }
}