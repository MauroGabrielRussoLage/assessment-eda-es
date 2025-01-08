package ec.com.sofka.transaction;

import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.generic.util.Entity;
import ec.com.sofka.transaction.value.TransactionId;
import ec.com.sofka.transaction.value.object.*;

public class Transaction extends Entity<TransactionId> {
    private Date date;
    private Type type;
    private Amount amount;
    private Description description;
    private AccountId destinationAccountId;
    private AccountId sourceAccountId;
    private Status status;

    public Transaction(Amount amount, Date date, Description description, AccountId destinationAccountId, AccountId sourceAccountId, Status status, Type type) {
        super(new TransactionId());
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.type = type;
    }

    protected Transaction(TransactionId id) {
        super(id);
    }

    public Transaction() {
        super(null);
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public AccountId getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(AccountId destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public AccountId getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(AccountId sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
