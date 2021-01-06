package de.mobilab.api.testdata;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Rate;
import java.util.Map;

public interface TestDataSpecification {

    Map<Currency, Rate> rates();

}
