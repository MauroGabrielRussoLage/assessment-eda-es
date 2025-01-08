package ec.com.sofka.response;


public class AccountResponse {
    protected String message;

    public AccountResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
