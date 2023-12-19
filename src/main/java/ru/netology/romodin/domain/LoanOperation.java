package ru.netology.romodin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanOperation extends Operation implements ConsolePrintable{

    private int loanId;

    @Override
    public void printToConsole() {

    }
}