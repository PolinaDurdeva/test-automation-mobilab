package de.mobilab.api.testdata;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Account;
import de.mobilab.api.dto.Rate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/**
 * Example to demonstrate how test data might be managed from files
 */
public class PropsBasedTestDataSpecificationImpl implements TestDataSpecification {

    private final Properties texts;

    public PropsBasedTestDataSpecificationImpl() {
        // can understand the environment and test contexts somehow from system properties, etc
        // Here just sample dummy implementation
        this.texts = new Properties();
        try {
            texts.load(Files.newBufferedReader(Paths.get("farm_texts.properties")));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Currency, Rate> rates() {
        return null;
    }
}
