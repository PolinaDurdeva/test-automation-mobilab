package de.mobilab.api.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Account;
import de.mobilab.api.services.AccountService;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest {

    private AccountService accountService;

    @BeforeClass
    public void init() {
        accountService = new AccountService();
    }

    @Test(dataProvider = "validUserAccounts")
    @Description("Verify that a user can create an account with valid data")
    public void userAccountWithValidData(Account account) {
        Account response = accountService
                .createAccount(account)
                .statusCode(201)
                .as(Account.class);
        assertThat(response.getId(), Matchers.not(Matchers.isEmptyOrNullString()));
        assertThat(response.getOwner(), Matchers.is(account.getOwner()));
        assertThat(response.getBalance(), Matchers.is(account.getBalance()));
        assertThat(response.getCurrency(), Matchers.is(account.getCurrency()));
    }

    @Test
    @Description("Verify that a user can not create an account with negative balance")
    public void userAccountWithNegativeBalance() {
        final Account account = new Account("A", BigDecimal.valueOf(-1.20), Currency.DOLLAR);
        accountService
                .createAccount(account)
                .statusCode(400);
    }

    @Test
    @Description("Verify that a user can not create an account with empty name")
    public void userAccountWithEmptyName() {
        final Account account = new Account("", BigDecimal.valueOf(38.9), Currency.EURO);
        accountService
                .createAccount(account)
                .statusCode(400);
    }

    @Test
    @Description("Verify that a user can get account details by id")
    public void userGetsAccountDetails() {
        final Account account = new Account("A", BigDecimal.valueOf(380.39), Currency.EURO);
        Account expectedAccount = accountService
                .createAccount(account)
                .statusCode(201)
                .as(Account.class);
        Account actualAccount = accountService.getAccount(expectedAccount.getId()).statusCode(200).as(Account.class);
        assertThat(actualAccount, Matchers.samePropertyValuesAs(expectedAccount));
    }

    @Test
    @Description("Verify account list has accounts")
    public void userAccountsList() {
        final Account account = new Account("A", BigDecimal.valueOf(380.39), Currency.EURO);
        accountService.createAccount(account);
        int listSize = accountService
                .getAccounts(0,10)
                .statusCode(200)
                .getJsonPath()
                .getList(".")
                .size();
        assertThat(listSize, Matchers.greaterThan(0));
    }

    @DataProvider(name = "validUserAccounts")
    public Object[][] getValidUserAccounts() {
        return new Object[][] {
                {new Account("Test", BigDecimal.valueOf(100), Currency.EURO)},
                {new Account("Robert de Müller", BigDecimal.valueOf(0), Currency.DOLLAR)},
                {new Account("Donaudampfschiffahrtselektrizitätenhauptbetriebswerkbauunterbeamtengesellschaft-justLongName", BigDecimal.valueOf(9999.983), Currency.EURO)}
        };
    }

}
