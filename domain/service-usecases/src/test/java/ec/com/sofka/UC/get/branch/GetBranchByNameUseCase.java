package ec.com.sofka.UC.get.branch;

import ec.com.sofka.branch.Branch;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetBranchByNameUseCase {
    private final BranchRepository repository;

    public GetBranchByNameUseCase(BranchRepository repository) {
        this.repository = repository;
    }

    public Mono<Branch> apply(String name) {
        return null;
    }
}
