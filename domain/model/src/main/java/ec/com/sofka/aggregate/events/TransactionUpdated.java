package ec.com.sofka.aggregate.events;

import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;

public class TransactionUpdated extends DomainEvent {
    private Date date;
    private Type type;
    private Amount amount;
    private Description description;
    private AccountId destinationAccountId;
    private AccountId sourceAccountId;
    private Status status;

    public TransactionUpdated(Amount amount, Date date, Description description, AccountId destinationAccountId, AccountId sourceAccountId, Status status, Type type) {
        super(EventsEnum.TRANSACTION_UPDATED.name());
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.type = type;
    }

    public Amount getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public Description getDescription() {
        return description;
    }

    public AccountId getDestinationAccountId() {
        return destinationAccountId;
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public Status getStatus() {
        return status;
    }

    public Type getType() {
        return type;
    }
}
