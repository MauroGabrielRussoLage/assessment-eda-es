package ec.com.sofka.account.value.object;

import ec.com.sofka.generic.interfaces.ValueObject;

import java.math.BigDecimal;

public class Balance implements ValueObject<BigDecimal> {
    private final BigDecimal value;

    public Balance(final BigDecimal value) {
        this.value = validate(value);
    }

    public static Balance of(final BigDecimal value) {
        return new Balance(value);
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    private BigDecimal validate(final BigDecimal value){
        //TODO Validaciones
        if(value == null){
            throw new IllegalArgumentException("The balance can't be null");
        }
        if(value.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("The balance must be greater than 0");
        }
        return value;
    }
}
