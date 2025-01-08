package ec.com.sofka.gateway.repository;

import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.transaction.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface TransactionRepository {

    Mono<TransactionDTO> createTransaction(Mono<TransactionDTO> transaction);

    Mono<TransactionDTO> findTransactionById(Mono<String> id);

    Flux<TransactionDTO> findAll();

    Flux<TransactionDTO> findTransactionsByDestinationAccountId(Mono<String> id);

    Flux<TransactionDTO> findTransactionsBySourceAccountId(Mono<String> id);

    Flux<TransactionDTO> findTransactionsByDate(Mono<LocalDateTime> date);

    Flux<TransactionDTO> findTransactionsByType(Mono<String> type);

    Mono<TransactionDTO> updateTransaction(Mono<TransactionDTO> transaction);

    Mono<Void> deleteById(Mono<String> id);
}
