package de.mobilab.ui.tests;

import com.codeborne.selenide.CollectionCondition;
import de.mobilab.api.currency.Currency;
import de.mobilab.api.dataprovider.AccountDataProvider;
import de.mobilab.api.dto.Account;
import de.mobilab.ui.pages.MainPage;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountListTest extends BaseTest {

    private Account account;

    @BeforeClass
    public void createAccount() {
        AccountDataProvider accountDataProvider = new AccountDataProvider();
        account = accountDataProvider.createAccount("A", BigDecimal.valueOf(1000.1), Currency.EURO);
    }

    @Test
    @Description("A new account appears in the account list with correct data")
    public void createdAccountDisplayedInTable() {
        MainPage.open()
                .goToAccountList()
                .findAccount(account.getId())
                .orElseThrow(() -> new AssertionError("Account #id" + account.getId() + "not found"))
                .shouldHave(CollectionCondition.texts(
                account.getId(),
                account.getOwner(),
                account.getBalance().toString(),
                account.getCurrency().name(),
                ""
        ));
    }

}
