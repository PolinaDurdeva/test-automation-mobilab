package de.mobilab.api.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Account;
import de.mobilab.api.dto.Rate;
import de.mobilab.api.dto.Transaction;
import de.mobilab.api.services.TransactionService;
import de.mobilab.api.dataprovider.AccountDataProvider;
import de.mobilab.api.testdata.StaticTestDataSpecification;
import de.mobilab.api.testdata.TestDataSpecification;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import java.util.Map;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TransactionWithDifferentCurrencyTest extends BaseTest {

    private TransactionService transactionService;
    private AccountDataProvider accountDataProvider;
    private TestDataSpecification testData;
    private Map<Currency, Rate> rates;

    @BeforeClass
    public void init() {
        transactionService = new TransactionService();
        accountDataProvider = new AccountDataProvider();
        testData = new StaticTestDataSpecification();
        rates = testData.rates();
    }

    @Test(dataProvider = "validTransactionsData")
    @Description("Transfer money from one account to another with different currencies")
    public void transferMoneyBetweenAccounts(Account source, Account destination, BigDecimal amount) {
        final Transaction transaction = new Transaction(amount, source, destination);
        Transaction response = transactionService.createTransaction(transaction).statusCode(201).as(Transaction.class);
        BigDecimal rate = rates.get(source.getCurrency()).getRate(destination.getCurrency());
        BigDecimal exchangeError = rates.get(source.getCurrency()).getError();
        BigDecimal expectedAmount = destination.deposit(amount.multiply(rate));
        assertThat(response.getSource().getBalance(), Matchers.comparesEqualTo(source.withdraw(amount)));
        assertThat(response.getDestination().getBalance(), Matchers.closeTo(expectedAmount, amount.multiply(exchangeError)));
    }


    @DataProvider(name = "validTransactionsData")
    public Object[][] getValidTransactionsData() {
        return new Object[][] {
                {
                    accountDataProvider.createAccount("A", BigDecimal.valueOf(777.7), Currency.EURO),
                    accountDataProvider.createAccount("B", BigDecimal.valueOf(90.1), Currency.DOLLAR),
                    BigDecimal.valueOf(199.9)},
                {
                    accountDataProvider.createAccount("A", BigDecimal.valueOf(777.7), Currency.DOLLAR),
                    accountDataProvider.createAccount("B", BigDecimal.valueOf(90.1), Currency.EURO),
                    BigDecimal.valueOf(400)},
                {
                    accountDataProvider.createAccount("A", BigDecimal.valueOf(777.7), Currency.EURO),
                    accountDataProvider.createAccount("B", BigDecimal.valueOf(90.1), Currency.DOLLAR),
                    BigDecimal.valueOf(777.7)},
        };
    }

}
