package de.mobilab.ui.tests;

import com.codeborne.selenide.Condition;
import de.mobilab.api.currency.Currency;
import de.mobilab.ui.components.Notification;
import de.mobilab.ui.pages.MainPage;
import io.qameta.allure.Description;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    @Test
    @Description("User is able to create account")
    public void userCanCreateAccount() {
        MainPage.open().goToCreateAccount().createAccount("Test", "100", Currency.EURO);
        at(Notification.class).getSuccessMessage().shouldBe(Condition.visible);
    }

    @Test
    @Description("User is not able to create account with empty balance")
    public void userCannotCreateAccountWithEmptyBalance() {
        MainPage.open().goToCreateAccount().createAccount("Test", "", Currency.EURO);
        at(Notification.class).getErrorMessage().shouldBe(Condition.visible);
    }

}
