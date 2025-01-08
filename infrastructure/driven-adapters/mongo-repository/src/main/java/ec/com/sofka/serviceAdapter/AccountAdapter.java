package ec.com.sofka.serviceAdapter;

import ec.com.sofka.data.AccountDocument;
import ec.com.sofka.data.CustomerDocument;
import ec.com.sofka.gateway.dto.AccountDTO;
import ec.com.sofka.gateway.repository.AccountRepository;
import ec.com.sofka.mapper.DTOToDocumentMapper;
import ec.com.sofka.mapper.DocumentToDTOMapper;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AccountAdapter implements AccountRepository {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public AccountAdapter(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<AccountDTO> createAccount(Mono<AccountDTO> account) {
        return account.flatMap(accountDTO -> DTOToDocumentMapper.toAccount.apply(Mono.just(accountDTO)))
                .flatMap(reactiveMongoTemplate::save)
                .flatMap(accountDocument -> DocumentToDTOMapper.toAccount.apply(Mono.just(accountDocument)));
    }

    @Override
    public Mono<AccountDTO> findAccountById(Mono<String> id) {
        return id.flatMap(accountId -> reactiveMongoTemplate.findById(accountId, AccountDocument.class))
                .flatMap(accountDocument -> {
                    AccountDTO accountDTO = new AccountDTO(
                            accountDocument.getAccountNumber(),
                            accountDocument.getAccountType(),
                            accountDocument.getBalance(),
                            accountDocument.getCustomerId(),
                            accountDocument.getId(),
                            accountDocument.getStatus()
                    );
                    return Mono.just(accountDTO);
                });
    }

    @Override
    public Flux<AccountDTO> getAccountsByCustomerId(Mono<String> customer_id) {
        return customer_id.flatMapMany(customerId -> {
            Query query = new Query(Criteria.where("_id").is(customerId));
            return reactiveMongoTemplate.findOne(query, AccountDocument.class)
                    .flatMap(accountDocument -> {
                        AccountDTO accountDTO = new AccountDTO(
                                accountDocument.getAccountNumber(),
                                accountDocument.getAccountType(),
                                accountDocument.getBalance(),
                                accountDocument.getCustomerId(),
                                accountDocument.getId(),
                                accountDocument.getStatus()
                        );
                        return Mono.just(accountDTO);
                    });
        });
    }

    @Override
    public Flux<AccountDTO> findAll() {
        return reactiveMongoTemplate.findAll(AccountDocument.class)
                .flatMap(accountDocument -> {
                    AccountDTO accountDTO = new AccountDTO(
                            accountDocument.getAccountNumber(),
                            accountDocument.getAccountType(),
                            accountDocument.getBalance(),
                            accountDocument.getCustomerId(),
                            accountDocument.getId(),
                            accountDocument.getStatus()
                    );
                    return Mono.just(accountDTO);
                });
    }

    @Override
    public Mono<AccountDTO> updateAccount(Mono<AccountDTO> account) {
        return account.flatMap(acc -> {
            Query query = new Query(Criteria.where("accountNumber").is(acc.getAccountNumber()));
            Update update = new Update()
                    .set("accountNumber", acc.getAccountNumber())
                    .set("accountType", acc.getAccountType())
                    .set("balance", acc.getBalance());
            return reactiveMongoTemplate.updateFirst(query, update, AccountDocument.class)
                    .flatMap(result -> result.getModifiedCount() > 0
                            ? Mono.just(acc)
                            : Mono.error(new RuntimeException("Account not found or failed to update")));
        });
    }

    @Override
    public Mono<Void> deleteById(Mono<String> id) {
        return id.flatMap(accountId -> reactiveMongoTemplate
                        .remove(Query.query(Criteria.where("_id").is(accountId)), AccountDocument.class))
                .then();
    }
}
