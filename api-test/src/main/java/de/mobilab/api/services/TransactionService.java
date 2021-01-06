package de.mobilab.api.services;

import de.mobilab.api.dto.Transaction;
import de.mobilab.api.responses.AssertableResponse;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import java.time.LocalDate;

public class TransactionService extends ApiService {

    @Step("Create transaction with params: {transaction.amount}, from {transaction.source} to {transaction.destination}")
    public AssertableResponse createTransaction(Transaction transaction) {
        return new AssertableResponse(RestAssured.given()
                .spec(specification)
                .body(transaction)
                .post(Endpoint.TRANSACTIONS));
    }

    @Step("Get all transactions from {from} - to {to}")
    public AssertableResponse getAllTransactions(LocalDate from, LocalDate to) {
        return new AssertableResponse(RestAssured.given()
                .spec(specification)
                .queryParam("from", from.toString())
                .queryParam("to", to.toString())
                .get(Endpoint.TRANSACTIONS));
    }

}
