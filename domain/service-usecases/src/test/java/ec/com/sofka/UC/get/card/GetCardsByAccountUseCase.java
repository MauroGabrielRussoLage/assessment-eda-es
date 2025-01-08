package ec.com.sofka.UC.get.card;

import ec.com.sofka.card.Card;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetCardsByAccountUseCase {
    private final CardRepository repository;

    public GetCardsByAccountUseCase(CardRepository repository) {
        this.repository = repository;
    }

    public Flux<Card> apply(int id) {
        return null;
    }
}
