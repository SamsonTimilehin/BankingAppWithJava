package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Current_Account extends Account{

    public Current_Account(){
     setStartDate(LocalDateTime.now());
    }
    public Current_Account(long accountNumber){
        this();
        setAccountNumber(accountNumber);
    }
    public Current_Account(long accountNumber, BigDecimal balance){
        this(accountNumber);
        setBalance(balance);
    }
}
