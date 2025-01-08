package ec.com.sofka.UC.update;


import ec.com.sofka.aggregate.Customer;
import ec.com.sofka.aggregate.value.object.*;
import ec.com.sofka.gateway.EventStore;
import ec.com.sofka.gateway.dto.CustomerDTO;
import ec.com.sofka.gateway.repository.CustomerRepository;
import ec.com.sofka.generic.object.Status;
import ec.com.sofka.request.CustomerRequest;
import ec.com.sofka.response.CustomerResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UpdateCustomerUseCase {
    private final EventStore repository;
    private final CustomerRepository customerRepository;

    public UpdateCustomerUseCase(EventStore repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    public Mono<CustomerResponse> apply(Mono<CustomerRequest> apply) {
        return apply.flatMap(customerRequestDTO -> {
                    Customer customer = new Customer(
                            Address.of(customerRequestDTO.getAddress()),
                            Email.of(customerRequestDTO.getEmail()),
                            FirstName.of(customerRequestDTO.getFirstName()),
                            LastName.of(customerRequestDTO.getLastName()),
                            Phone.of(customerRequestDTO.getPhone()),
                            Status.of(customerRequestDTO.getStatus())
                    );
                    customer.getUncommittedEvents().forEach(domainEvent -> {
                        domainEvent.setVersion(domainEvent.getVersion() + 1);
                        repository.save(Mono.just(domainEvent)).subscribe();
                    });
                    customer.markEventsAsCommitted();
                    CustomerDTO customerDTO = new CustomerDTO(
                            customer.getId().getValue(),
                            customer.getAddress().getValue(),
                            customer.getEmail().getValue(),
                            customer.getFirstName().getValue(),
                            customer.getLastName().getValue(),
                            customer.getPhone().getValue(),
                            customer.getStatus().getValue()
                    );
                    return customerRepository.updateCustomer(Mono.just(customerDTO)).map(response -> new CustomerResponse("ok"));
                }
        );
    }
}
