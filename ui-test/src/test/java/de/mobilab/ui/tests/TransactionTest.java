package de.mobilab.ui.tests;

import com.codeborne.selenide.Condition;
import de.mobilab.api.currency.Currency;
import de.mobilab.api.dataprovider.AccountDataProvider;
import de.mobilab.api.dto.Account;
import de.mobilab.ui.components.Notification;
import de.mobilab.ui.pages.MainPage;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TransactionTest extends BaseTest {

    private Account accountSource;
    private Account accountDestination;

    @BeforeClass
    public void createTwoAccounts() {
        AccountDataProvider accountDataProvider = new AccountDataProvider();
        accountSource = accountDataProvider.createAccount("A", BigDecimal.valueOf(1000.1), Currency.EURO);
        accountDestination = accountDataProvider.createAccount("B", BigDecimal.valueOf(123.5), Currency.EURO);
    }

    @Test
    @Description("User can create a transaction")
    public void userCanCreateTransaction() {
        MainPage.open()
                .goToCreateTransaction()
                .createTransaction("100", accountSource.getId(), accountDestination.getId());
        at(Notification.class).getSuccessMessage().shouldBe(Condition.visible);
    }

    @Test
    @Description("User sees an error message if the transfer amount exceeds the balance")
    public void userExceedsBalance() {
        MainPage.open()
                .goToCreateTransaction()
                .createTransaction("1500", accountSource.getId(), accountDestination.getId());
        at(Notification.class).getErrorMessage().shouldBe(Condition.visible);
    }

    @Test
    @Description("User sees an error message if a source account is equal to a destination account")
    public void destinationEqualsToSource() {
        MainPage.open()
                .goToCreateTransaction()
                .createTransaction("10", accountSource.getId(), accountSource.getId());
        at(Notification.class).getErrorMessage().shouldBe(Condition.visible);
    }

}
