package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.netology.romodin.interfaces.ConsolePrintable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operation implements ConsolePrintable {
    private Integer id;
    private Integer sum;
    private Currency currency;
    private String merchant;
    private Integer customerId;

    public void printToConsole() {
        System.out.println("ID: " + id);
        System.out.println("Сумма операции: " + sum + " " + currency);
        System.out.println("Продавец: " + merchant);
    }
}