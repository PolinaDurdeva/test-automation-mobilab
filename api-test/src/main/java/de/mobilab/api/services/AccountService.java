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

    @Step("Get an account with id: {id}")
    public AssertableResponse getAccount(String id) {
        return new AssertableResponse(RestAssured.given()
                .spec(specification)
                .get(Endpoint.ACCOUNT, id));
    }

    @Step("Get account list with params: page={page}, size={size}")
    public AssertableResponse getAccounts(int page, int size) {
        return new AssertableResponse(RestAssured.given()
                .spec(specification)
                .queryParam("page", page)
                .queryParam("size", size)
                .get(Endpoint.ACCOUNTS));
    }

}
