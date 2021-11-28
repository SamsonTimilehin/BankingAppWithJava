package Test.engines;

import datastore.CustomerRepo;
import datastore.LoanRequestStatus;
import datastore.LoanType;
import engines.LoanEngine;
import engines.LoanEngineByBalance;
import entity.Account;
import entity.Customer;
import entity.LoanRequest;
import entity.Savings_Account;
import exception.BankLoanException;
import exception.BankingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AccountService;
import services.AccountServiceImpl;
import services.BankService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class LoanEngineByBalanceTest {

    private LoanRequest johnLoanRequest;
    LoanEngine loanEngine;
    private AccountService accountService;
    private Customer john;

    @BeforeEach
    void setUp() throws BankingException {
        accountService = new AccountServiceImpl();
        loanEngine = new LoanEngineByBalance();

        johnLoanRequest = new LoanRequest();
        johnLoanRequest.setApplyDate(LocalDateTime.now());
        johnLoanRequest.getInterestRate(0.1);
        johnLoanRequest.setStatus(LoanRequestStatus.NEW);
        johnLoanRequest.setTenor(25);
        johnLoanRequest.setTypeOfLoan(LoanType.SME);
        Optional<Customer> optionalCustomer = CustomerRepo.getCustomers().values().stream().findFirst();
        john = (optionalCustomer.isPresent()) ? optionalCustomer.get() : null;
        assertNotNull(john);
    }
    @AfterEach
    void tearDown() {
        BankService.reset ();
        CustomerRepo.reset ();
    }


    @Test
    void approveLoanRequestWithoutCustomer(){

        assertThrows (BankLoanException.class,
                () -> loanEngine.calculateAmountAutoApproved (null, new Savings_Account(  ))) ;
    }

    @Test
    void approveLoanRequestWithNullAccount(){
        assertThrows (BankLoanException.class, () -> loanEngine.calculateAmountAutoApproved (john, null));
    }

    @Test
    void calculateAmountAutoApproved(){
        try{
            Account johnCurrentAccount = accountService.findAccount (1000110002);
            johnLoanRequest.setLoanAmount (BigDecimal.valueOf (9000000));
            johnCurrentAccount.setAccountLoanRequest (johnLoanRequest);
            BigDecimal amountApproved = loanEngine.calculateAmountAutoApproved (john, johnCurrentAccount);
            assertEquals (10090000.0, amountApproved.intValue ());
        } catch (BankingException ex) {
            ex.printStackTrace ( );
        }
    }

    @Test
    void calculateAmountAutoApprovedForCustomerWithNegativeBalance(){
        try{
            Account johnCurrentAccount = accountService.findAccount (1000110002);
            johnCurrentAccount.setBalance (BigDecimal.valueOf (-900000));

            johnCurrentAccount.setAccountLoanRequest (johnLoanRequest);
            BigDecimal amountApproved = loanEngine.calculateAmountAutoApproved (john, johnCurrentAccount);
            assertEquals (0, amountApproved.intValue ());
        } catch (BankingException ex) {
            ex.printStackTrace ( );
        }
    }
}