package ec.com.sofka.gateway.repository;

import ec.com.sofka.account.Account;
import ec.com.sofka.gateway.dto.AccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountRepository {
    Mono<AccountDTO> createAccount(Mono<AccountDTO> account);

    Mono<AccountDTO> findAccountById(Mono<String> id);

    Flux<AccountDTO> getAccountsByCustomerId(Mono<String> customer_id);

    Flux<AccountDTO> findAll();

    Mono<AccountDTO> updateAccount(Mono<AccountDTO> account);

    Mono<Void> deleteById(Mono<String> id);
}