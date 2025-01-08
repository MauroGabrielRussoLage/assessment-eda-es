package ec.com.sofka.aggregate;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.account.value.object.AccountNumber;
import ec.com.sofka.account.value.object.AccountType;
import ec.com.sofka.account.value.object.Balance;
import ec.com.sofka.aggregate.events.*;
import ec.com.sofka.aggregate.value.CustomerId;
import ec.com.sofka.aggregate.value.object.*;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.AggregateRoot;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;

import java.util.List;

public class Customer extends AggregateRoot<CustomerId> {
    protected FirstName firstName;
    protected LastName lastName;
    protected Email email;
    protected Phone phone;
    protected Address address;
    protected Status status;
    protected List<Account> accounts;
    protected List<Transaction> transactions;

    public Customer(final String id) {
        super(CustomerId.of(id));
        setSubscription(new CustomerHandler(this));
    }

    public Customer() {
        super(new CustomerId());
        setSubscription(new CustomerHandler(this));
    }

    public Customer( Address address, Email email, FirstName firstName, LastName lastName, Phone phone, Status status) {
        super(new CustomerId());
        setSubscription(new CustomerHandler(this));
        addEvent(new CustomerCreated(address.getValue(), email.getValue(), firstName.getValue(), lastName.getValue(), phone.getValue(), status.getValue())).apply();
    }

    public void addAccount(AccountNumber accountNumber, AccountType accountType, Balance balance, Status status, CustomerId customerId) {
        AccountCreated accountCreatedEvent = new AccountCreated(
                accountNumber.getValue(),
                accountType.getValue(),
                balance.getValue(),
                status.getValue(),
                customerId.getValue()
        );
        addEvent(accountCreatedEvent).apply();
    }

    public void updateAccount(AccountNumber accountNumber, AccountType accountType, Balance balance, Status status, CustomerId customerId) {
        AccountUpdated accountUpdatedEvent = new AccountUpdated(
                accountNumber,
                accountType,
                balance,
                status,
                customerId
        );
        addEvent(accountUpdatedEvent).apply();
    }

    public void addTransaction(Amount amount, Date date, Description description, AccountId destinationAccountId, AccountId sourceAccountId, Status status, Type type) {
        TransactionCreated transactionCreatedEvent = new TransactionCreated(
                amount.getValue(),
                date.getValue(),
                description.getValue(),
                destinationAccountId.getValue(),
                sourceAccountId.getValue(),
                status.getValue(),
                type.getValue()
        );
        addEvent(transactionCreatedEvent).apply();
    }

    public void updateTransaction(Amount amount, Date date, Description description, AccountId destinationAccountId, AccountId sourceAccountId, Status status, Type type) {
        TransactionUpdated transactionUpdatedEvent = new TransactionUpdated(
                amount,
                date,
                description,
                destinationAccountId,
                sourceAccountId,
                status,
                type
        );
        addEvent(transactionUpdatedEvent).apply();
    }

    //To rebuild the aggregate
    public static Customer from(final String id, List<DomainEvent> events) {
        Customer customer = new Customer(id);
        events.stream()
                .filter(event -> id.equals(event.getAggregateRootId()))
                .forEach((event) -> customer.addEvent(event).apply());
        return customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
