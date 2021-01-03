package de.mobilab.api.services;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.responses.AccountResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import java.util.Map;

public class AccountService extends ApiService {

    public AccountService() {

    }

    @Step("Create Account")
    public AccountResponse createAccount(String ownerName, Double balance, Currency currency) {
        return RestAssured.given()
                .spec(specification)
                .body(Map.of("owner", ownerName, "balance", balance.toString(), "currency", currency))
                .post(Endpoint.ACCOUNTS)
                .then()
                .statusCode(201)
                .extract()
                .body()
                .as(AccountResponse.class);
    }

}
