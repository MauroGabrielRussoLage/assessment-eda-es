package ec.com.sofka.aggregate.events;

import ec.com.sofka.account.value.object.AccountNumber;
import ec.com.sofka.account.value.object.AccountType;
import ec.com.sofka.account.value.object.Balance;
import ec.com.sofka.aggregate.value.CustomerId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.object.Status;

public class AccountUpdated extends DomainEvent {
    private final AccountNumber accountNumber;
    private final AccountType accountType;
    private final Balance balance;
    private final Status status;
    private final CustomerId customerId;

    public AccountUpdated(AccountNumber accountNumber, AccountType accountType, Balance balance, Status status, CustomerId customerId) {
        super(EventsEnum.ACCOUNT_UPDATED.name());
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }

    public AccountNumber getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public Balance getBalance() {
        return balance;
    }

    public Status getStatus() {
        return status;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }
}
