package de.mobilab.api.testdata;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Rate;
import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.Map;

public class StaticTestDataSpecification implements TestDataSpecification {


    public StaticTestDataSpecification() {
        // can understand the environment and test contexts somehow from system properties, etc
    }


    @Override
    public Map<Currency, Rate> rates() {
        BigDecimal rateError = BigDecimal.valueOf(0.1);
        EnumMap<Currency, Rate> rates = new EnumMap<>(Currency.class);
        rates.put(Currency.DOLLAR, new Rate(Currency.DOLLAR, rateError).addRate(Currency.EURO, BigDecimal.valueOf(0.81)));
        rates.put(Currency.EURO, new Rate(Currency.DOLLAR, rateError).addRate(Currency.DOLLAR, BigDecimal.valueOf(1.23)));
        return rates;
    }

}
