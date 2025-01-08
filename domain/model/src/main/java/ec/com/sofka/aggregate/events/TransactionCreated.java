package ec.com.sofka.aggregate.events;

import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.generic.domain.DomainEvent;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionCreated extends DomainEvent {
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private String destinationAccountId;
    private String sourceAccountId;
    private String status;

    public TransactionCreated(BigDecimal amount, LocalDateTime date, String description, String destinationAccountId, String sourceAccountId, String status, String type) {
        super(EventsEnum.TRANSACTION_CREATED.name());
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.type = type;
    }

    public TransactionCreated() {
        super(EventsEnum.TRANSACTION_CREATED.name());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(String destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public String getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
