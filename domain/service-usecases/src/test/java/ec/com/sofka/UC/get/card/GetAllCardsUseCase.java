package ec.com.sofka.UC.get.card;

import ec.com.sofka.card.Card;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetAllCardsUseCase {
    private final CardRepository repository;

    public GetAllCardsUseCase(CardRepository repository) {
        this.repository = repository;
    }

    public Flux<Card> apply() {
        return null;
    }
}
