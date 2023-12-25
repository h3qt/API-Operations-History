package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOperation extends Operation {

    private int loanId;

    @Override
    public void printToConsole() {
        System.out.println("ID: " + getId());
        System.out.println("ID займа: " + loanId);
        System.out.println("Сумма займа: " + getSum() + " " + getCurrency());
        System.out.println("Продавец: " + getMerchant());
    }
}