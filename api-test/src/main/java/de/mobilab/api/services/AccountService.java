package de.mobilab.api.services;

import de.mobilab.api.dto.Account;
import de.mobilab.api.responses.AssertableResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;

public class AccountService extends ApiService {

    public AccountService() {

    }

    @Step("Create an account with params: {account.owner}, {account.balance}, {account.currency}")
    public AssertableResponse createAccount(Account account) {
        return new AssertableResponse(RestAssured.given()
                .spec(specification)
                .body(account)
                .post(Endpoint.ACCOUNTS));
    }

}
