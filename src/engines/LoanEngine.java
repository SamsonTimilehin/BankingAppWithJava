package engines;

import entity.Account;
import entity.Customer;
import exception.BankLoanException;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface LoanEngine {

    BigDecimal calculateAmountAutoApproved(Customer customer, Account accountSeekingLoan) throws BankLoanException;
    public BigDecimal getLoanPercentage(long determinant);

    default void validateLoanRequest(Customer customer, Account accountSeekingLoan) throws BankLoanException {
        if (customer == null){
            throw new BankLoanException ( "An account is required to process loan request" );
        }
        LocalDateTime today = LocalDateTime.now();

        if (today.isBefore(customer.getRelationshipStartDate())){
            throw new BankLoanException("Customer relationship start date is after today");
        }
        validateLoanRequest (accountSeekingLoan);
    }

    default void validateLoanRequest( Account accountSeekingLoan) throws BankLoanException{
        if (accountSeekingLoan == null){
            throw new BankLoanException ( "An account is required to process loan request" );
        }
        if (accountSeekingLoan.getAccountLoanRequest () == null){
            throw new BankLoanException( "No loan request provided for processing" );
        }
    }

    default BigDecimal getTotalCustomerBalance(Customer customer){
        BigDecimal totalCustomerBalance = BigDecimal.ZERO;
        for(Account customerAccount : customer.getAccount ( )) {
            totalCustomerBalance = totalCustomerBalance.add (customerAccount.getBalance ( ));
        }
        return totalCustomerBalance;
    }
}
