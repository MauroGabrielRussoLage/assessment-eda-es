package ec.com.sofka.UC.update;

import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.EventStore;
import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.request.TransactionRequest;
import ec.com.sofka.response.TransactionResponse;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.repository.TransactionRepository;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateTransactionUseCase {
    private final EventStore repository;
    private final TransactionRepository transactionRepository;
    private final BusMessage busMessage;

    public UpdateTransactionUseCase(EventStore repository, TransactionRepository transactionRepository, BusMessage busMessage) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
        this.busMessage = busMessage;
    }

    public Mono<TransactionResponse> apply(Mono<TransactionRequest> transactionRequestMono) {
        return transactionRequestMono.flatMap(request -> repository.findAggregate(Mono.just(request.getAggregateId()))
                .collectList()
                .flatMap(events -> {
                    Customer customer = Customer.from(request.getAggregateId(), events);
                    customer.addTransaction(
                            Amount.of(request.getAmount()),
                            Date.of(request.getDate()),
                            Description.of(request.getDescription()),
                            AccountId.of(request.getDestinationAccountId()),
                            AccountId.of(request.getSourceAccountId()),
                            Status.of(request.getStatus()),
                            Type.of(request.getType())
                    );
                    customer.getUncommittedEvents().forEach(domainEvent -> {
                        repository.save(Mono.just(domainEvent)).subscribe();
                    });
                    customer.markEventsAsCommitted();
                    Transaction transaction = customer.getTransactions().getLast();
                    TransactionDTO transactionDTO = new TransactionDTO(
                            transaction.getAmount().getValue(),
                            transaction.getDate().getValue(),
                            transaction.getDescription().getValue(),
                            transaction.getDestinationAccountId().getValue(),
                            transaction.getId().getValue(),
                            transaction.getSourceAccountId().getValue(),
                            transaction.getStatus().getValue(),
                            transaction.getType().getValue()
                    );
                    return transactionRepository.updateTransaction(Mono.just(transactionDTO)).map(response -> new TransactionResponse("ok"));
                }));
    }
}
