package ec.com.sofka.UC.create;

import ec.com.sofka.account.Account;
import ec.com.sofka.account.value.object.AccountNumber;
import ec.com.sofka.account.value.object.AccountType;
import ec.com.sofka.account.value.object.Balance;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.aggregate.value.CustomerId;
import ec.com.sofka.gateway.EventStore;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.repository.AccountRepository;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.request.AccountRequest;
import ec.com.sofka.response.AccountResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateAccountUseCase {
    private final AccountRepository accountRepository;
    private final EventStore eventRepository;

    public CreateAccountUseCase(AccountRepository accountRepository, EventStore eventRepository) {
        this.accountRepository = accountRepository;
        this.eventRepository = eventRepository;
    }

    public Mono<AccountResponse> apply(Mono<AccountRequest> createAccountRequest) {
        return createAccountRequest.flatMap(request -> eventRepository.findAggregate(Mono.just(request.getAggregateId()))
                .collectList()
                .flatMap(events -> {
                    Customer customer = Customer.from(request.getAggregateId(), events);
                    customer.addAccount(
                            AccountNumber.of(request.getAccountNumber()),
                            AccountType.of(request.getAccountType()),
                            Balance.of(request.getBalance()),
                            Status.of(request.getStatus()),
                            CustomerId.of(request.getCustomerId())
                    );
                    customer.getUncommittedEvents().forEach(domainEvent -> {
                        eventRepository.save(Mono.just(domainEvent)).subscribe();
                    });
                    customer.markEventsAsCommitted();
                    AccountDTO accountDTO = new AccountDTO(
                            request.getAccountNumber(),
                            request.getAccountType(),
                            request.getBalance(),
                            request.getCustomerId(),
                            null,
                            request.getStatus()
                    );
                    return accountRepository.createAccount(Mono.just(accountDTO)).map(response -> new AccountResponse("ok"));
                }));
    }
}