package ec.com.sofka.response;


public class CustomerResponse {
    protected String message;

    public CustomerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
