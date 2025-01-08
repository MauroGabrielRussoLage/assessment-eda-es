package ec.com.sofka.request;

import ec.com.sofka.generic.util.Request;

public class CustomerRequest extends Request {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String phone;
    protected String address;
    protected String status;

    // Constructores existentes
    public CustomerRequest(String address, String email, String firstName, String lastName, String phone, String status) {
        super(null);
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
    }

    public CustomerRequest(String aggregateId) {
        super(aggregateId);
    }

    // Métodos getter y setter
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String status;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public CustomerRequest build() {
            return new CustomerRequest(address, email, firstName, lastName, phone, status);
        }
    }
}
