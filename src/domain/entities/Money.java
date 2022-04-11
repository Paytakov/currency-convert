package domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Money {
    private final BigDecimal value;
    private final String currency;

    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
        this.validate();
    }

    private void validate() {
        List<String> invalidFields = new ArrayList<>();

        if (this.value == null || this.value.compareTo(BigDecimal.ZERO) <= 0) {
            invalidFields.add("Value must be non-null non-negative!");
        }

        if (this.currency == null || this.currency.length() != 3) {
            invalidFields.add("Currency must be non-null string of size 3");
        }

        if (!invalidFields.isEmpty()) {
            throw new IllegalArgumentException("" + invalidFields);
        }
    }
    public BigDecimal getValue() {
        return this.value;
    }

    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return "{" +
                "value: " + value +
                ", currency: '" + currency + '\'' +
                '}';
    }
}
