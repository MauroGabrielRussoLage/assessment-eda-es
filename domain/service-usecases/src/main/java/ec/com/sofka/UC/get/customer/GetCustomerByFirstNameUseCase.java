package ec.com.sofka.UC.get.customer;

import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.repository.CustomerRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetCustomerByFirstNameUseCase {
    private final CustomerRepository repository;

    public GetCustomerByFirstNameUseCase(CustomerRepository repository) {
        this.repository = repository;
    }

    public Mono<CustomerDTO> apply(String firstName) {
        return null;
    }
}
