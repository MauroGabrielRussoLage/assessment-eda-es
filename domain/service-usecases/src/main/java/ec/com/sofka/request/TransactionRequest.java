package ec.com.sofka.request;

import ec.com.sofka.generic.util.Request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest extends Request {
    private LocalDateTime date;
    private String type;
    private BigDecimal amount;
    private String description;
    private String destinationAccountId;
    private String sourceAccountId;
    private String status;

    public TransactionRequest(String aggregateId, BigDecimal amount, LocalDateTime date, String description, String destinationAccountId, String sourceAccountId, String status, String type) {
        super(aggregateId);
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.destinationAccountId = destinationAccountId;
        this.sourceAccountId = sourceAccountId;
        this.status = status;
        this.type = type;
    }

    public TransactionRequest(String aggregateId) {
        super(aggregateId);
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

    public static class Builder {
        private LocalDateTime date;
        private String type;
        private BigDecimal amount;
        private String description;
        private String destinationAccountId;
        private String sourceAccountId;
        private String status;
        private String aggregateId;

        public TransactionRequest.Builder withDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public TransactionRequest.Builder withType(String type) {
            this.type = type;
            return this;
        }

        public TransactionRequest.Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public TransactionRequest.Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TransactionRequest.Builder withDestinationAccountId(String destinationAccountId) {
            this.destinationAccountId = destinationAccountId;
            return this;
        }

        public TransactionRequest.Builder withSourceAccountId(String sourceAccountId) {
            this.sourceAccountId = sourceAccountId;
            return this;
        }

        public TransactionRequest.Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public TransactionRequest.Builder withAggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public TransactionRequest build() {
            return new TransactionRequest(aggregateId, amount, date, description, destinationAccountId, sourceAccountId, status, type);
        }
    }
}
