package exception;

public class BankLoanException extends BankingException{
    public BankLoanException() {
    }

    public BankLoanException(String message) {
        super(message);
    }

    public BankLoanException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankLoanException(Throwable cause) {
        super(cause);
    }

    public BankLoanException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
