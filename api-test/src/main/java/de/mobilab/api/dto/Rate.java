package de.mobilab.api.dto;

import de.mobilab.api.currency.Currency;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class Rate {

    private final Currency base;
    private final Map<Currency, BigDecimal> rates;
    private final BigDecimal error;

    public Rate(Currency base) {
        this.base = base;
        this.error = BigDecimal.valueOf(0.0);
        rates = new EnumMap<>(Currency.class);
    }

    public Rate(Currency base, BigDecimal error) {
        this.base = base;
        this.error = error;
        rates = new EnumMap<>(Currency.class);
    }

    public Rate addRate(Currency currency, BigDecimal value) {
        rates.put(currency, value);
        return this;
    }

    public Currency getBase() {
        return base;
    }

    public BigDecimal getRate(Currency currency) {
        return rates.get(currency);
    }

    public BigDecimal getError() {
        return error;
    }

}
