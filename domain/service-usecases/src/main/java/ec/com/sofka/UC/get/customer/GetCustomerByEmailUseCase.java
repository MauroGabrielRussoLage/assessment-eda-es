package ec.com.sofka.UC.get.customer;


import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetCustomerByEmailUseCase {
    private final CustomerRepository repository;

    public GetCustomerByEmailUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<CustomerDTO> apply(String email) {
        return repository.findCustomerByEmail(Mono.just(email));
    }
}
