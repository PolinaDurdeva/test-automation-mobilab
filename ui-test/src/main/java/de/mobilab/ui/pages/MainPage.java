package de.mobilab.ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainPage implements Page {

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);
    private final SelenideElement createAccountButton = $("#createaccbtn");
    private final SelenideElement createTransactionButton = $("#createtransbtn");
    private final SelenideElement accountListButton = $("#acclistbtn");
    private final SelenideElement transactionListButton = $("#translistbtn");

    @Step("Open home page")
    public static MainPage open(){
        LOGGER.info("Open home page");
        Selenide.open("/");
        return new MainPage();
    }

    @Step("Navigate to create account")
    public NewAccountPage goToCreateAccount() {
        LOGGER.info("Navigate to create account");
        createAccountButton.click();
        return new NewAccountPage();
    }

    @Step("Navigate to create transaction")
    public NewTransactionPage goToCreateTransaction() {
        LOGGER.info("Navigate to create transaction");
        createTransactionButton.click();
        return new NewTransactionPage();
    }

    @Step("Navigate to account list")
    public AccountListPage goToAccountList() {
        LOGGER.info("Navigate to account list");
        accountListButton.click();
        return new AccountListPage();
    }

    @Step("Navigate to transaction list")
    public TransactionListPage goToTransactionList() {
        LOGGER.info("Navigate to transaction list");
        transactionListButton.click();
        return new TransactionListPage();
    }

}
