package de.mobilab.ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewTransactionPage implements Page {

    private static final Logger LOGGER = LogManager.getLogger(NewTransactionPage.class);
    private final SelenideElement amountField = $("#amount");
    private final SelenideElement sourceSelector = $("#source");
    private final SelenideElement destinationSelector = $("#destination");
    private final SelenideElement currencyField = $("#currency");
    private final SelenideElement submitButton = $("#transsubmit");
    private final SelenideElement clearButton = $("#transclear");

    @Step("Create transaction with parameters: {amount}, {sourceId}, {destinationId}")
    public void createTransaction(String amount, String sourceId, String destinationId) {
        LOGGER.info("Create transaction with parameters: {}, {}, {}", amount, sourceId, destinationId);
        amountField.sendKeys(amount);
        sourceSelector.click();
        sourceSelector.$("option[value*='" + sourceId + "']").click();
        destinationSelector.$("option[value*='" + destinationId + "']").click();
        submitButton.click();
    }

}
