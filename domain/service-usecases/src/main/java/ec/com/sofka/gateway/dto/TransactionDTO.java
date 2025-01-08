package ec.com.sofka.gateway.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    protected String id;
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private String destinationAccountId;
    private String sourceAccountId;
    private String status;

    public TransactionDTO(BigDecimal amount, LocalDateTime date, String description, String destinationAccountId, String id, String sourceAccountId, String status, String type) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.id = id;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.type = type;
    }

    public TransactionDTO() {}

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
