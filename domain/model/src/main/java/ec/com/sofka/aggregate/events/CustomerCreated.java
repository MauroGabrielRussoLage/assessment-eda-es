package ec.com.sofka.aggregate.events;

import ec.com.sofka.generic.domain.DomainEvent;

public class CustomerCreated extends DomainEvent {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String status;

    public CustomerCreated(String address, String email, String firstName, String lastName, String phone, String status) {
        super(EventsEnum.CUSTOMER_CREATED.name());
        this.address = address;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.status = status;
    }

    public CustomerCreated() {
        super(EventsEnum.CUSTOMER_CREATED.name());
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getStatus() {
        return status;
    }
}
