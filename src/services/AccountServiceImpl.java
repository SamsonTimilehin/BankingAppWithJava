package services;

import datastore.AccountType;
import datastore.CustomerRepo;
import entity.Account;
import entity.Current_Account;
import entity.Customer;
import entity.Savings_Account;
import exception.BankTransactionException;
import exception.BankingException;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService{
    @Override
    public long openAccount(Customer theCustomer, AccountType type) throws BankingException {

        long accountNumber = BigDecimal.ZERO.longValue ();
        if (type == AccountType.SAVINGS_ACCOUNT){
            accountNumber = savingsAccount(theCustomer);
        }else if(type == AccountType.CURRENT_ACCOUNT){
            accountNumber = currentAccount(theCustomer);
        }

        return accountNumber;
    }

    @Override
    public long savingsAccount(Customer theCustomer) throws BankingException {
        if(theCustomer == null){
            throw new BankingException("customer and account type required to open new account");
        }
        Savings_Account newAccount = new Savings_Account();
        if(accountTypeExists(theCustomer, newAccount.getClass().getTypeName())){
            throw new BankingException("Customer already have the account-type");
        }
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        theCustomer.getAccount().add(newAccount);
        CustomerRepo.getCustomers().put(theCustomer.getBvn(), theCustomer);
        return newAccount.getAccountNumber();
    }

    private boolean accountTypeExists(Customer aCustomer, String typeName) {
        boolean accountTypeExists = false;
        for(Account customerAccount : aCustomer.getAccount()){
            if (customerAccount.getClass().getTypeName().equals(typeName)){
                accountTypeExists = true;
                break;
            }
        }
        return accountTypeExists;

    }

    @Override
    public long currentAccount(Customer theCustomer) throws BankingException {
        if(theCustomer == null){
            throw new BankingException("customer and account type required to open new account");
        }
        Current_Account newAccount = new Current_Account();
        newAccount.setAccountNumber(BankService.generateAccountNumber());
        theCustomer.getAccount().add(newAccount);
        CustomerRepo.getCustomers().put(theCustomer.getBvn(), theCustomer);
        return newAccount.getAccountNumber();
    }

    @Override
    public BigDecimal deposit(BigDecimal amount, long accountNumber) throws BankTransactionException {
            Account account = findAccount(accountNumber);
            validateTransaction(amount,account);

            BigDecimal newBalance = account.getBalance().add(amount);
            account.setBalance(newBalance);

        return newBalance;
    }

    @Override
    public Account findAccount(long accountNumber) {

        Account foundAccount = null;
        boolean accountFound = false;

        for(Customer customer : CustomerRepo.getCustomers().values()){

            for(Account anAccount : customer.getAccount()){
                if(anAccount.getAccountNumber() == accountNumber){
                    foundAccount = anAccount;
                    accountFound = true;
                    break;
                }
            }
            if(accountFound){
                break;
            }
        }
        return foundAccount;
    }

    private void validateTransaction(BigDecimal amount, Account account) throws BankTransactionException {
        if(amount.compareTo(BigDecimal.ZERO) < 0){
            throw new BankTransactionException("Transaction amount cannot be negative");
        }
        if(account == null){
            throw new BankTransactionException("Transaction account not found");
        }
    }
}
