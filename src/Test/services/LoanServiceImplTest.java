package Test.services;

import datastore.CustomerRepo;
import datastore.LoanRequestStatus;
import datastore.LoanType;
import entity.*;
import exception.BankLoanException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {

    private LoanRequest johnLoanRequest;
    private LoanService loanService;
    private AccountService accountService;


    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl( );
        loanService = new LoanServiceImpl();
        johnLoanRequest = new LoanRequest ();
        johnLoanRequest.setApplyDate (LocalDateTime.now());
        johnLoanRequest.getInterestRate (0.1);
        johnLoanRequest.setStatus (LoanRequestStatus.NEW);
        johnLoanRequest.setTenor (25);
        johnLoanRequest.setTypeOfLoan (LoanType.SME);
    }

    @AfterEach
    void tearDown() {
        BankService.reset ();
        CustomerRepo.reset ();
    }

    @Test
    void approveLoanRequestWithNullAccount(){
        assertThrows (BankLoanException.class, () -> loanService.approveLoanRequest (null));
    }

    @Test
    void approveLoanRequestWithNullLoanRequest(){
        Current_Account accountWithoutLoanRequest = new Current_Account (  );
        assertThrows (BankLoanException.class, () -> loanService.approveLoanRequest (accountWithoutLoanRequest));
    }

    @Test
    void approveLoanRequestWithoutCustomer(){

        assertThrows (BankLoanException.class,
                () -> loanService.approveLoanRequest (new Savings_Account(), null ));
    }

    @Test
    void approveLoanRequestWithCustomerButWithoutAccount(){

        assertThrows (BankLoanException.class,
                () -> loanService.approveLoanRequest (null, new Customer() ));
    }

    @Test
    void approveLoanRequestWithAccountBalance(){
        try{
            Account johnCurrentAccount = accountService.findAccount (1000011003);
            assertNull (johnCurrentAccount.getAccountLoanRequest ());
            johnLoanRequest.setLoanAmount (BigDecimal.valueOf (10000));
            johnCurrentAccount.setAccountLoanRequest (johnLoanRequest);

            LoanRequest processedLoanRequest = loanService.approveLoanRequest (johnCurrentAccount);
            assertEquals (LoanRequestStatus.APPROVED, processedLoanRequest.getStatus ());
        } catch (BankLoanException cause) {
            cause.printStackTrace ();
        }

    }

    @Test
    void approveLoanRequestWithAccountBalanceAndHighLoanRequestAmount(){
        try{
            Account johnCurrentAccount = accountService.findAccount (1000011003);
            assertNull (johnCurrentAccount.getAccountLoanRequest ());
            johnLoanRequest.setLoanAmount (BigDecimal.valueOf (90000000));
            johnCurrentAccount.setAccountLoanRequest (johnLoanRequest);

            LoanRequest processedLoanRequest = loanService.approveLoanRequest (johnCurrentAccount);
            assertEquals (LoanRequestStatus.PENDING, processedLoanRequest.getStatus ());
        } catch (BankLoanException cause) {
            cause.printStackTrace ( );
        }

    }

    @Test
    void approveLoanRequestWithLengthOfRelationship(){

            Account johnSavingsAccount = accountService.findAccount (1000011003);
            Optional<Customer> optionalCustomer = CustomerRepo.getCustomers ().values ().stream ().findFirst ();
            Customer john = optionalCustomer.isPresent () ? optionalCustomer.get ( ) : null;
            assertNotNull (john);
            john.setRelationshipStartDate (johnSavingsAccount.getStartDate ().minusYears (2));
            johnLoanRequest.setLoanAmount (BigDecimal.valueOf (3000000));
            johnSavingsAccount.setAccountLoanRequest (johnLoanRequest);




    }

    @Test
    void approveLoanRequestWithTotalVolume(){
        try{
            Account johnSavingsAccount = accountService.findAccount (1000011003);
            Optional<Customer> optionalCustomer = CustomerRepo.getCustomers ().values ().stream ().findFirst ();
            Customer john = optionalCustomer.isPresent () ? optionalCustomer.get ( ) : null;
            assertNotNull (john);
            john.setRelationshipStartDate (johnSavingsAccount.getStartDate ().minusYears (2));
            assertEquals (BigDecimal.valueOf (450000), johnSavingsAccount.getBalance ());
            johnLoanRequest.setLoanAmount (BigDecimal.valueOf (3000000));
            johnSavingsAccount.setAccountLoanRequest (johnLoanRequest);
            LoanRequest johnLoanRequest = loanService.approveLoanRequest (johnSavingsAccount, john);
            assertEquals (LoanRequestStatus.APPROVED, johnLoanRequest.getStatus ());

        }catch (BankLoanException cause){
            cause.printStackTrace ();
        }

    }

}