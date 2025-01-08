package ec.com.sofka.UC.delete;

import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteCustomerUseCase {
    private final CustomerRepository repository;

    public DeleteCustomerUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<Void> apply(String id) {
        return repository.deleteById(Mono.just(id));
    }
}
