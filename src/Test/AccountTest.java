package Test;

import datastore.CustomerRepo;
import entity.Account;
import entity.Customer;
import entity.Savings_Account;
import services.BankService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    Account peterSavingsAccount;
    Customer peter;
    @BeforeEach
    void setUp() {
       peterSavingsAccount = new Savings_Account();
       peter = new Customer();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAccount(){
        peterSavingsAccount = new Savings_Account();
        peterSavingsAccount.setAccountPin("10001100");
        peterSavingsAccount.setAccountPin("1234");
        peterSavingsAccount.setAccountNumber(BankService.generateAccountNumber());
        peterSavingsAccount.setBalance(BigDecimal.valueOf(550));
        assertEquals(BigDecimal.valueOf(550), peterSavingsAccount.getBalance());

        peter.setBvn(BankService.generateBvn());
        peter.setEmail("peter@gmail.com");
        peter.setFirstName("John");
        peter.setSurname("Maxwell");
        peter.setPassword("200145");
        peter.setPhone("08145321702");

        assertFalse(CustomerRepo.getCustomers().isEmpty());
    }
}