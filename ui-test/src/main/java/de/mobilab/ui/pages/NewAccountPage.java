package de.mobilab.ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import de.mobilab.api.currency.Currency;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NewAccountPage implements Page {

    private static final Logger LOGGER = LogManager.getLogger(NewAccountPage.class);
    private final SelenideElement ownerField = $("#owner");
    private final SelenideElement balanceField = $("#balance");
    private final SelenideElement currencySelector = $("#currency");
    private final SelenideElement submitButton = $("#accountsubmit");
    private final SelenideElement clearButton = $("#accountclear");

    @Step("Create account with parameters: {owner}, {balance}, {currency}")
    public void createAccount(String owner, String balance, Currency currency) {
        LOGGER.info("Create account with parameters: {}, {}, {}", owner, balance, currency);
        ownerField.sendKeys(owner);
        balanceField.sendKeys(balance);
        currencySelector.selectOptionByValue(currency.name());
        submitButton.click();
    }


}
