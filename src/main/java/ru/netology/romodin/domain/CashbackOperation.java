package ru.netology.romodin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CashbackOperation extends Operation {

    private int cashbackAmount;

    @Override
    public void printToConsole() {
        System.out.println("ID: " + getId());
        System.out.println("Сумма займа: " + getSum() + " " + getCurrency());
        System.out.println("Сумма кэшбека: " + cashbackAmount);
        System.out.println("Продавец: " + getMerchant());
    }
}