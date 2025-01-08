package ec.com.sofka.gateway.repository;

import ec.com.sofka.gateway.dto.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<CustomerDTO> createCustomer(Mono<CustomerDTO> customer);

    Mono<CustomerDTO> findCustomerById(Mono<Integer> id);

    Mono<CustomerDTO> findCustomerByFirstName(Mono<String> first_name);

    Mono<CustomerDTO> findCustomerByLastName(Mono<String> last_name);

    Mono<CustomerDTO> findCustomerByEmail(Mono<String> email);

    Flux<CustomerDTO> findAll();

    Mono<CustomerDTO> updateCustomer(Mono<CustomerDTO> customer);

    Mono<Void> deleteById(Mono<String> id);
}
