package Test.services;

import datastore.AccountType;
import datastore.CustomerRepo;
import entity.Customer;
import exception.BankingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AccountService;
import services.AccountServiceImpl;
import services.BankService;

import java.util.Optional;

import static datastore.AccountType.CURRENT_ACCOUNT;
import static datastore.AccountType.SAVINGS_ACCOUNT;
import static org.junit.jupiter.api.Assertions.*;

class AccountServiceImplTest {
  private AccountService accountService;
  private Customer peter;
    private Customer john;
    @BeforeEach
    void setUp() {
        accountService = new AccountServiceImpl();
        peter = new Customer();
        peter.setBvn(BankService.generateBvn());
        peter.setEmail("peter@gmail.com");
        peter.setFirstName("Peter");
        peter.setSurname("Maxwell");
        peter.setPassword("200145");
        peter.setPhone("08145321702");

        john = new Customer();
        john.setBvn(BankService.generateBvn());
        john.setEmail("John@gmail.com");
        john.setFirstName("John");
        john.setSurname("Guido");
        john.setPassword("138200");
        john.setPhone("08175124589");
    }

    @AfterEach
    void tearDown() {
        BankService.reset ();
        CustomerRepo.reset ();

    }
    @Test
    void openSavingsAccount(){

        assertTrue(john.getAccount().isEmpty());
        assertEquals(1000011003, BankService.getCurrentAccountNumber());
        try{
            long newAccountNumber = accountService.openAccount (john, SAVINGS_ACCOUNT);
            assertFalse (CustomerRepo.getCustomers ().isEmpty ());
            assertEquals(1000011004, BankService.getCurrentAccountNumber());

            assertTrue(CustomerRepo.getCustomers().containsKey(john.getBvn()));

            assertFalse(john.getAccount().isEmpty());
            System.out.println(john.getAccount().get(0));
            assertEquals (newAccountNumber, john.getAccount ().get (0).getAccountNumber ());
        }catch (BankingException cause){
            cause.printStackTrace();
        }
    }
    @Test
    void withdrawWithNoCustomer(){

        assertThrows(BankingException.class,()-> accountService.openAccount(null, SAVINGS_ACCOUNT));
    }

    @Test
    void openTheSameTypeOfAccountForTheSamePerson(){
        Optional<Customer> johnOptional = CustomerRepo.getCustomers().values().stream().findFirst();
        Customer john = johnOptional.get();
        System.out.println(john);

        assertEquals(1000011003, BankService.getCurrentAccountNumber());
        assertNotNull(john);
        assertNotNull(john.getAccount());
        assertFalse(john.getAccount().isEmpty());
       assertEquals(SAVINGS_ACCOUNT.toString(), john.getAccount().get(0).getClass().getSimpleName().toUpperCase());
        System.out.println(john.getAccount().get(0).getClass().getSimpleName());

        assertThrows(BankingException.class,()-> accountService.openAccount(john,SAVINGS_ACCOUNT));
        assertEquals(1000011003, BankService.getCurrentAccountNumber());
        assertEquals(1, john.getAccount().size());
    }

    @Test
    void openCurrentAccount(){

        assertTrue(john.getAccount().isEmpty());
        assertEquals(1000011003, BankService.getCurrentAccountNumber());

        try {
            long newAccount = accountService.openAccount(john, CURRENT_ACCOUNT);
            assertEquals(1000011004,BankService.getCurrentAccountNumber());
            assertTrue(CustomerRepo.getCustomers().containsKey(john.getBvn()));
            assertFalse(john.getAccount().isEmpty());
            assertEquals(newAccount, john.getAccount().get(0).getAccountNumber());

        }catch (BankingException cause){
            cause.printStackTrace();
        }
    }

    @Test
    void openDifferentTypeOfAccountForTheSameCustomer(){
        try{
          long newAccount = accountService.openAccount(john, SAVINGS_ACCOUNT);
          assertEquals(1000011004, BankService.getCurrentAccountNumber());
          assertTrue(CustomerRepo.getCustomers().containsKey(john.getBvn()));
          assertEquals(1, john.getAccount().size());
          assertEquals(newAccount, john.getAccount().get(0).getAccountNumber());

          long newCurrentAccount = accountService.openAccount(john, CURRENT_ACCOUNT);
          assertEquals(1000011005, BankService.getCurrentAccountNumber());
          assertTrue(CustomerRepo.getCustomers().containsKey(john.getBvn()));
          assertEquals(2, john.getAccount().size());
          assertEquals(newCurrentAccount, john.getAccount().get(1).getAccountNumber());
        }catch (BankingException cause){
            cause.printStackTrace();
        }

    }
    @Test
    void openSavingsAccountForNewCustomers(){
        assertTrue(peter.getAccount().isEmpty());
        assertEquals(1000011003, BankService.getCurrentAccountNumber());
        try{
            long newSavingsAccountNumber = accountService.openAccount(peter,SAVINGS_ACCOUNT);
            assertFalse (CustomerRepo.getCustomers ( ).isEmpty ());
            assertEquals (1000011004, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomers ( ).containsKey (peter.getBvn ( )));
            assertFalse (peter.getAccount().isEmpty());
            assertEquals (newSavingsAccountNumber, peter.getAccount().get(0).getAccountNumber());

            long newSavingsAccountNumber1 = accountService.openAccount(john, SAVINGS_ACCOUNT);
            assertEquals(4, CustomerRepo.getCustomers().size());
            assertEquals (1000011005, BankService.getCurrentAccountNumber ( ));
            assertTrue (CustomerRepo.getCustomers ( ).containsKey (john.getBvn ( )));
            assertFalse (john.getAccount().isEmpty());
            assertEquals (1, john.getAccount().size());
            assertEquals (newSavingsAccountNumber1, john.getAccount().get(0).getAccountNumber());
            assertEquals (1, peter.getAccount().size());
        }catch(BankingException cause){
            cause.printStackTrace();
        }
    }

    @Test
    void deposit(){

    }
}