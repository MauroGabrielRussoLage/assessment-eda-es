package ec.com.sofka.aggregate.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

public class LastName implements ValueObject<String> {
    private final String value;

    public LastName(final String value) {
        this.value = validate(value);
    }

    public static LastName of(final String value) {
        return new LastName(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value){
        //TODO Validaciones
        return value;
    }
}
