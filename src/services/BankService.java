package services;

public class BankService {
    private static long currentBvn = 1001;
    private static long currentAccountNumber = 1000011003;
    private static long currentTransactionId = 0;


    public static long generateAccountNumber() {
        currentAccountNumber++;
       return currentAccountNumber;
    }
    public static long getCurrentAccountNumber(){
        return currentAccountNumber;
    }

    public static long generateBvn() {
        currentBvn++;
        return currentBvn;
    }
    public static long getCurrentBvn(){
        return currentBvn;
    }
    public static void reset(){
        currentBvn = 1001;
        currentAccountNumber = 1000011003;
    }

    public static long getCurrentTransactionId() {
        currentTransactionId++;
        return currentTransactionId;
    }
}
