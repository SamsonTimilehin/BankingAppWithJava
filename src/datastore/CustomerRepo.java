package datastore;

import entity.Account;
import entity.Current_Account;
import entity.Customer;
import entity.Savings_Account;
import services.BankService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CustomerRepo {

    private static Map<Long, Customer> customers = new HashMap<>();

    public static Map<Long, Customer> getCustomers() {
        return customers;
    }
    private static void setCustomers(Map<Long, Customer> customers){
        CustomerRepo.customers = customers;
    }


    static{
        reset();
    }

    public static void reset(){


        Customer peter = new Customer();
        peter.setBvn(BankService.generateBvn());
        peter.setEmail("peter@gmail.com");
        peter.setFirstName("Peter");
        peter.setSurname("Maxwell");
        peter.setPassword("200145");
        peter.setPhone("08145321702");
        Account peterSavingAccount = new Savings_Account(1000011004);
        peter.getAccount().add(peterSavingAccount); //note
        customers.put(peter.getBvn(), peter);


        Customer john = new Customer();
        john.setBvn(BankService.generateBvn());
        john.setEmail("john@gmail.com");
        john.setFirstName("John");
        john.setSurname("Guido");
        john.setPassword("138200");
        john.setPhone("08175124589");
        Account johnCurrentAccount = new Current_Account(1000011002);
        john.getAccount().add(johnCurrentAccount);
        customers.put(john.getBvn(), john);
        Account johnSavingsAccount = new Savings_Account(1000011003);
        johnSavingsAccount.setBalance(BigDecimal.valueOf(450000));
        john.getAccount().add(johnSavingsAccount);
        customers.put(john.getBvn(), john);
    }
}
