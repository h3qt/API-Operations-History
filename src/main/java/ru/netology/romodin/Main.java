package ru.netology.romodin;

import ru.netology.romodin.domain.Customer;
import ru.netology.romodin.domain.Operation;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public final static int MAX_OPERATIONS = 4;
    public final static int MAX_CUSTOMERS = 2;
    public final static Operation[] operations = new Operation[MAX_OPERATIONS];
    public final static Customer[] customers = new Customer[MAX_CUSTOMERS];
    public final static int[][] statement = new int[MAX_CUSTOMERS][MAX_OPERATIONS / MAX_CUSTOMERS];
    public final static int[] customer_operations_count = new int[MAX_CUSTOMERS];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Customer.inputCustomer(scanner);
        Operation.inputOperation(scanner);

        System.out.println(Arrays.toString(customers));
        System.out.println(Arrays.toString(operations));
        System.out.println(Arrays.deepToString(statement));
        System.out.println(Arrays.toString(customer_operations_count));
    }
}