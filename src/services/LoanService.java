package services;

import entity.Account;
import entity.Customer;
import entity.LoanRequest;
import exception.BankLoanException;

public interface LoanService {

    LoanRequest approveLoanRequest(Account loanAccount) throws BankLoanException;
    LoanRequest approveLoanRequest(Account loanService, Customer theCustomer) throws BankLoanException;
}
