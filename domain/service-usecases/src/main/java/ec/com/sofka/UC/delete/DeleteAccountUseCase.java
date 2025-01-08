package ec.com.sofka.UC.delete;

import ec.com.sofka.gateway.repository.AccountRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteAccountUseCase {
    private final AccountRepository repository;

    public DeleteAccountUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> apply(String id) {
        return repository.deleteById(Mono.just(id));
    }
}
