package ru.netology.romodin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CashbackOperation extends Operation implements ConsolePrintable{

    private int cashbackAmount;

    @Override
    public void printToConsole() {

    }
}