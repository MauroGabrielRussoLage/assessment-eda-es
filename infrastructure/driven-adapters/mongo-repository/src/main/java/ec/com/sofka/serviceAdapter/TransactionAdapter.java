package ec.com.sofka.serviceAdapter;

import ec.com.sofka.gateway.dto.TransactionDTO;
import ec.com.sofka.transaction.Transaction;
import ec.com.sofka.data.TransactionDocument;
import ec.com.sofka.gateway.repository.TransactionRepository;
import ec.com.sofka.mapper.DocumentToDTOMapper;
import ec.com.sofka.mapper.DTOToDocumentMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Repository
public class TransactionAdapter implements TransactionRepository {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public TransactionAdapter(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<TransactionDTO> createTransaction(Mono<TransactionDTO> transaction) {
        return transaction.flatMap(transactionModel -> DTOToDocumentMapper.toTransaction.apply(Mono.just(transactionModel)))
                .flatMap(reactiveMongoTemplate::save)
                .flatMap(transactionDocument -> DocumentToDTOMapper.toTransaction.apply(Mono.just(transactionDocument)));
    }

    @Override
    public Mono<TransactionDTO> findTransactionById(Mono<String> id) {
        return id.flatMap(transactionId -> reactiveMongoTemplate.findById(transactionId, TransactionDocument.class))
                .flatMap(transactionDocument -> DocumentToDTOMapper.toTransaction.apply(Mono.just(transactionDocument)));
    }

    @Override
    public Flux<TransactionDTO> findAll() {
        return reactiveMongoTemplate.findAll(TransactionDocument.class)
                .flatMap(transactionDocument -> DocumentToDTOMapper.toTransaction.apply(Mono.just(transactionDocument)));
    }

    @Override
    public Flux<TransactionDTO> findTransactionsByDestinationAccountId(Mono<String> id) {
        return id.flatMapMany(branchId -> {
            Query query = new Query(Criteria.where("destinationAccount").is(branchId));
            return reactiveMongoTemplate.findOne(query, TransactionDocument.class)
                    .flatMap(transactionDocument -> DocumentToDTOMapper
                            .toTransaction.apply(Mono.just(transactionDocument)));
        });
    }

    @Override
    public Flux<TransactionDTO> findTransactionsBySourceAccountId(Mono<String> id) {
        return id.flatMapMany(branchId -> {
            Query query = new Query(Criteria.where("sourceAccount").is(branchId));
            return reactiveMongoTemplate.findOne(query, TransactionDocument.class)
                    .flatMap(transactionDocument -> DocumentToDTOMapper
                            .toTransaction.apply(Mono.just(transactionDocument)));
        });
    }

    @Override
    public Flux<TransactionDTO> findTransactionsByDate(Mono<LocalDateTime> date) {
        return date.flatMapMany(localDateTime ->
                        reactiveMongoTemplate.find(Query.query(Criteria.where("date").is(localDateTime)),
                                TransactionDocument.class))
                .flatMap(transactionDocument -> DocumentToDTOMapper.toTransaction.apply(Mono.just(transactionDocument)));
    }

    @Override
    public Flux<TransactionDTO> findTransactionsByType(Mono<String> type) {
        return type.flatMapMany(strType ->
                        reactiveMongoTemplate.find(Query.query(Criteria.where("type").is(strType)),
                                TransactionDocument.class))
                .flatMap(transactionDocument -> DocumentToDTOMapper.toTransaction.apply(Mono.just(transactionDocument)));
    }

    @Override
    public Mono<TransactionDTO> updateTransaction(Mono<TransactionDTO> transaction) {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Mono<String> id) {
        return id.flatMap(transactionId -> reactiveMongoTemplate
                        .remove(Query.query(Criteria.where("_id").is(transactionId)), TransactionDocument.class))
                .then();
    }
}
