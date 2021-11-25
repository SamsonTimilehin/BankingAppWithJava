package entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Savings_Account extends Account{

    public Savings_Account(){
     setStartDate(LocalDateTime.now());
    }
    public Savings_Account(long accountNumber){
        this();
        setAccountNumber(accountNumber);
    }
    public Savings_Account(long accountNumber,  BigDecimal balance) {
        this (accountNumber);
        setBalance (balance);
    }
}
