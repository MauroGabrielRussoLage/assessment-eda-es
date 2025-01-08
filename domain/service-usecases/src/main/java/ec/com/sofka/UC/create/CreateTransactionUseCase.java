package ec.com.sofka.UC.create;

import ec.com.sofka.account.value.AccountId;
import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.gateway.EventStore;
import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.request.TransactionRequest;
import ec.com.sofka.response.TransactionResponse;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.gateway.BusMessage;
import ec.com.sofka.gateway.repository.TransactionRepository;
import ec.com.sofka.transaction.value.object.Amount;
import ec.com.sofka.transaction.value.object.Date;
import ec.com.sofka.transaction.value.object.Description;
import ec.com.sofka.transaction.value.object.Type;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CreateTransactionUseCase {

    private final EventStore repository;
    private final TransactionRepository transactionRepository;
    private final BusMessage busMessage;

    public CreateTransactionUseCase(EventStore repository, TransactionRepository transactionRepository, BusMessage busMessage) {
        this.repository = repository;
        this.transactionRepository = transactionRepository;
        this.busMessage = busMessage;
    }

    public Mono<TransactionResponse> apply(Mono<TransactionRequest> transactionRequest) {
        return transactionRequest.flatMap(request -> repository.findAggregate(Mono.just(request.getAggregateId()))
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
                    TransactionDTO transactionDTO = new TransactionDTO(
                            request.getAmount(),
                            request.getDate(),
                            request.getDescription(),
                            request.getDestinationAccountId(),
                            null,
                            request.getSourceAccountId(),
                            request.getStatus(),
                            request.getType()
                    );
                    return transactionRepository.createTransaction(Mono.just(transactionDTO)).map(response -> new TransactionResponse("ok"));
                }));
    }
}
