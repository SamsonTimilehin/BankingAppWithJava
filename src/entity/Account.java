package entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Account {

    private long accountNumber;
    private BigDecimal balance = BigDecimal.ZERO;
    private String accountPin;
    private LoanRequest accountLoanRequest;
    private Set<BankTransaction> transactions = new HashSet<>();
    private LocalDateTime startDate;

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAccountPin() {
        return accountPin;
    }

    public void setAccountPin(String accountPin) {
        this.accountPin = accountPin;
    }

    public LoanRequest getAccountLoanRequest() {
        return accountLoanRequest;
    }

    public void setAccountLoanRequest(LoanRequest accountLoanRequest) {
        this.accountLoanRequest = accountLoanRequest;
    }

    public Set<BankTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<BankTransaction> transactions) {
        this.transactions = transactions;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", accountPin='" + accountPin + '\'' +
                '}';
    }
}
