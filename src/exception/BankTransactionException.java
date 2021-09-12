package exception;

public class BankTransactionException extends BankingException {
    public BankTransactionException() {
        super();
    }

    public BankTransactionException(String message) {
        super(message);
    }

    public BankTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public BankTransactionException(Throwable cause) {
        super(cause);
    }

    public BankTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
