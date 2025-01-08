package ec.com.sofka.response;


public class TransactionResponse {
    protected String message;

    public TransactionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
