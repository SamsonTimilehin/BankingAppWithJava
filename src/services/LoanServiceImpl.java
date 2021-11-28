package services;

import datastore.LoanRequestStatus;
import entity.Account;
import entity.Customer;
import entity.LoanRequest;
import exception.BankLoanException;

import java.math.BigDecimal;

public class LoanServiceImpl implements LoanService{


    @Override
    public LoanRequest approveLoanRequest(Account accountSeekingLoan) throws BankLoanException {
     validateLoanRequest(accountSeekingLoan);
     LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
     theLoanRequest.setStatus(decideOnLoanRequest(accountSeekingLoan));

        return theLoanRequest;
    }

    @Override
    public LoanRequest approveLoanRequest(Account accountSeekingLoan, Customer customer) throws BankLoanException {
        this.validateLoanRequest(customer,accountSeekingLoan);
        LoanRequestStatus decision = decideOnLoanRequestWithTotalCustomerBalance(accountSeekingLoan,customer);
        LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
        theLoanRequest.setStatus(decision);

        if(decision != LoanRequestStatus.APPROVED){
            theLoanRequest = approveLoanRequest(accountSeekingLoan);
        }
        return theLoanRequest;
    }

    public LoanRequestStatus decideOnLoanRequest(Account accountSeekingLoan) {
    LoanRequestStatus decision = decideOnLoanRequestWithAccountBalance(accountSeekingLoan);

    return decision;
    }

    public LoanRequestStatus decideOnLoanRequestWithAccountBalance(Account accountSeekingLoan) {

        LoanRequestStatus decision = LoanRequestStatus.PENDING;
        LoanRequest theLoanRequest = accountSeekingLoan.getAccountLoanRequest();
        BigDecimal bankAccountBalancePercentage = BigDecimal.valueOf(0.2);

        BigDecimal loanAmountApprovedAutomatically = accountSeekingLoan.getBalance().multiply(bankAccountBalancePercentage);
        if(theLoanRequest.getLoanAmount().compareTo(loanAmountApprovedAutomatically) < BigDecimal.ZERO.intValue()){
            decision = LoanRequestStatus.APPROVED;
        }
        return decision;
    }

    public LoanRequestStatus decideOnLoanRequestWithTotalCustomerBalance(Account accountSeekingLoan, Customer customer) {
        LoanRequestStatus decision = LoanRequestStatus.PENDING;
        BigDecimal relationshipVolumePercentage = BigDecimal.valueOf(0.2);

        BigDecimal totalCustomerBalance = BigDecimal.ZERO;
        if(customer.getAccount().size() > BigDecimal.ZERO.intValue()){
            for(Account customerAccount : customer.getAccount()){
                totalCustomerBalance = totalCustomerBalance.add(customerAccount.getBalance());

            }
        }
        BigDecimal loanAmountApprovedAutomatically = totalCustomerBalance.multiply(relationshipVolumePercentage);
        if(accountSeekingLoan.getAccountLoanRequest().getLoanAmount().compareTo(loanAmountApprovedAutomatically) < BigDecimal.ZERO.intValue()){
            decision = LoanRequestStatus.APPROVED;
        }
        return decision;
    }

    private void validateLoanRequest(Account accountSeekingLoan) throws BankLoanException {
        if(accountSeekingLoan == null){
            throw new BankLoanException("An account is required to process loan request");

        }
        if(accountSeekingLoan.getAccountLoanRequest() == null){
            throw new BankLoanException("No loan request provided for process");
        }
    }

    private void validateLoanRequest(Customer customer, Account accountSeekingLoan) throws BankLoanException {
        if(customer == null){
            throw new BankLoanException("An account is required to process loan request");
        }
        this.validateLoanRequest(accountSeekingLoan);
    }
}
