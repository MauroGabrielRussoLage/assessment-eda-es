package ec.com.sofka.UC.get.card;

import ec.com.sofka.card.Card;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GetCardByIdUseCase {
    private final CardRepository repository;

    public GetCardByIdUseCase(CardRepository repository) {
        this.repository = repository;
    }

    public Mono<Card> apply(int id) {
        return null;
    }
}
