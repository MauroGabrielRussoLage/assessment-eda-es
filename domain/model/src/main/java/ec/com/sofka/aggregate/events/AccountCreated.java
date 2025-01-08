package ec.com.sofka.aggregate.events;

import ec.com.sofka.account.value.object.AccountNumber;
import ec.com.sofka.account.value.object.AccountType;
import ec.com.sofka.account.value.object.Balance;
import ec.com.sofka.aggregate.value.CustomerId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.object.Status;

import java.math.BigDecimal;

public class AccountCreated extends DomainEvent {
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String status;
    private String customerId;

    public AccountCreated(String accountNumber, String accountType, BigDecimal balance, String customerId, String status) {
        super(EventsEnum.ACCOUNT_CREATED.name());
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.customerId = customerId;
        this.status = status;
    }

    public AccountCreated() {
        super(EventsEnum.ACCOUNT_CREATED.name());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
