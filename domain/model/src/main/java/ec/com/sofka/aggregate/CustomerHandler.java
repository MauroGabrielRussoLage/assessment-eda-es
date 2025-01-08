package ec.com.sofka.aggregate;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.account.value.object.AccountNumber;
import ec.com.sofka.account.value.object.AccountType;
import ec.com.sofka.account.value.object.Balance;
import ec.com.sofka.aggregate.events.*;
import ec.com.sofka.aggregate.value.CustomerId;
import ec.com.sofka.aggregate.value.object.*;
import ec.com.sofka.generic.domain.DomainActionsContainer;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;

public class CustomerHandler extends DomainActionsContainer {
    public CustomerHandler(Customer customer) {
        addDomainActions((CustomerCreated event) -> {
            customer.firstName = new FirstName(event.getFirstName());
            customer.lastName = new LastName(event.getLastName());
            customer.email = new Email(event.getEmail());
            customer.phone = new Phone(event.getPhone());
            customer.address = new Address(event.getAddress());
            customer.status = new Status(event.getStatus());
        });
        addDomainActions((AccountCreated event) -> {
            Account account = new Account(AccountNumber.of(event.getAccountNumber()),
                    AccountType.of(event.getAccountType()),
                    Balance.of(event.getBalance()),
                    Status.of(event.getStatus()),
                    CustomerId.of(event.getAggregateRootId()));
            customer.accounts.add(account);
        });
        addDomainActions((TransactionCreated event) -> {
            Transaction transaction = new Transaction(Amount.of(event.getAmount()),
                    Date.of(event.getDate()),
                    Description.of(event.getDescription()),
                    AccountId.of(event.getDestinationAccountId()),
                    AccountId.of(event.getSourceAccountId()),
                    Status.of(event.getStatus()),
                    Type.of(event.getType())
                    );
            customer.transactions.add(transaction);
        });
        addDomainActions((CustomerUpdated event) -> {
            customer.firstName = new FirstName(event.getFirstName());
            customer.lastName = new LastName(event.getLastName());
            customer.email = new Email(event.getEmail());
            customer.phone = new Phone(event.getPhone());
            customer.address = new Address(event.getAddress());
            customer.status = new Status(event.getStatus());
        });
        addDomainActions((AccountUpdated event) -> {
            Account account = new Account(event.getAccountNumber(),
                    event.getAccountType(),
                    event.getBalance(),
                    event.getStatus(),
                    CustomerId.of(event.getAggregateRootId()));
            customer.accounts.set(customer.accounts.indexOf(account), account);
        });
        addDomainActions((TransactionUpdated event) -> {
            Transaction transaction = new Transaction(event.getAmount(),
                    event.getDate(),
                    event.getDescription(),
                    event.getDestinationAccountId(),
                    event.getSourceAccountId(),
                    event.getStatus(),
                    event.getType()
            );
            customer.transactions.set(customer.transactions.indexOf(transaction), transaction);
        });
    }
}