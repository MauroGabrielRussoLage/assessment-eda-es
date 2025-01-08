package ec.com.sofka.request;

import ec.com.sofka.generic.util.Request;

import java.math.BigDecimal;

public class AccountRequest extends Request {
    private String accountNumber;
    private String accountType;
    private BigDecimal balance;
    private String status;
    private String customerId;

    protected AccountRequest(String aggregateId) {
        super(aggregateId);
    }

    public AccountRequest(String aggregateId,String accountNumber, String accountType, BigDecimal balance, String status, String customerId) {
        super(aggregateId);
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getStatus() {
        return status;
    }

    public String getCustomerId() {
        return customerId;
    }

    public static class Builder {
        private String accountNumber;
        private String accountType;
        private BigDecimal balance;
        private String status;
        private String customerId;
        private String aggregateId;

        public Builder withAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder withAccountType(String accountType) {
            this.accountType = accountType;
            return this;
        }

        public Builder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder withAggregateId(String aggregateId) {
            this.aggregateId = aggregateId;
            return this;
        }

        public AccountRequest build() {
            return new AccountRequest(aggregateId, accountNumber, accountType, balance, status, customerId);
        }
    }
}
