package services;

import datastore.AccountType;
import entity.Customer;
import exception.BankingException;

import java.math.BigDecimal;

public interface AccountService {

    long openAccount(Customer theCustomer, AccountType type) throws BankingException;

    long savingsAccount(Customer theCustomer) throws BankingException;

    long currentAccount(Customer theCustomer) throws BankingException;

    BigDecimal deposit(BigDecimal amount, long accountNumber);

    long findAccount(long accountNumber);
}
