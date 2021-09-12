package services;

import datastore.AccountType;
import entity.Account;
import entity.Customer;
import exception.BankTransactionException;
import exception.BankingException;
import exception.InsufficientFundException;

import java.math.BigDecimal;

public interface AccountService {

    long openAccount(Customer theCustomer, AccountType type) throws BankingException;

    long savingsAccount(Customer theCustomer) throws BankingException;

    long currentAccount(Customer theCustomer) throws BankingException;

    BigDecimal deposit(BigDecimal amount, long accountNumber) throws BankTransactionException;

    Account findAccount(long accountNumber);

    BigDecimal withdraw(BigDecimal amount, long accountNumber) throws BankTransactionException, InsufficientFundException;
}
