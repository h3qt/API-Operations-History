package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}