package de.mobilab.api.tests;

import de.mobilab.api.configurations.TestConfiguration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public BaseTest() {
    }

    @BeforeSuite
    public void setup() {
        TestConfiguration cfg = ConfigFactory.create(TestConfiguration.class, System.getProperties());
        RestAssured.baseURI = cfg.apiUrl();
    }

}
