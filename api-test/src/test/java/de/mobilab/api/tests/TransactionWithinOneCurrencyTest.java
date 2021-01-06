package de.mobilab.api.tests;

import static org.hamcrest.MatcherAssert.assertThat;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Account;
import de.mobilab.api.dto.Transaction;
import de.mobilab.api.services.TransactionService;
import de.mobilab.api.dataprovider.AccountDataProvider;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransactionWithinOneCurrencyTest extends BaseTest {

    private final TransactionService transactionService = new TransactionService();
    private final AccountDataProvider accountDataProvider = new AccountDataProvider();
    private Account accountSource;
    private Account accountDestination;

    @BeforeMethod
    public void createTwoAccount() {
        accountSource = accountDataProvider.createAccount("A", BigDecimal.valueOf(1999), Currency.EURO);
        accountDestination = accountDataProvider.createAccount("B", BigDecimal.valueOf(137.7), Currency.EURO);
    }

    @Test
    @Description("Transfer money from one account to another with the same currency")
    public void transferMoneyBetweenAccounts() {
        final BigDecimal amount = BigDecimal.valueOf(199.9);
        Transaction transaction = new Transaction(amount, accountSource, accountDestination);
        Transaction response = transactionService
                .createTransaction(transaction)
                .statusCode(201)
                .as(Transaction.class);
        assertThat(response.getSource().getBalance(), Matchers.comparesEqualTo(accountSource.withdraw(amount)));
        assertThat(response.getDestination().getBalance(), Matchers.comparesEqualTo(accountDestination.deposit(amount)));
    }

    @Test
    @Description("Transfer an amount that exceeded the account balance")
    public void transferExceededBalanceAmount() {
        final BigDecimal amount = accountSource.getBalance().add(BigDecimal.valueOf(1));
        Transaction transaction = new Transaction(amount, accountSource, accountDestination);
        String message = transactionService
                .createTransaction(transaction)
                .statusCode(400)
                .getJsonPath()
                .getString("messages[0]");
        assertThat(message, Matchers.is("Sufficient funds required to perform this operation."));
    }

    @Test
    @Description("Transfer negative value")
    public void transferNegativeValue() {
        final BigDecimal amount = BigDecimal.valueOf(-20);
        Transaction transaction = new Transaction(amount, accountSource, accountDestination);
        transactionService.createTransaction(transaction).statusCode(400);
    }

    @Test
    @Description("Transfer zero amount is forbidden")
    public void transferZeroAmount() {
        final BigDecimal amount = BigDecimal.valueOf(0.0);
        Transaction transaction = new Transaction(amount, accountSource, accountDestination);
        transactionService.createTransaction(transaction).statusCode(400);
    }

    @Test
    @Description("Verify transaction list API")
    public void transactionListContainsTransactions() {
        final BigDecimal amount = BigDecimal.valueOf(213.78);
        final Transaction transaction = new Transaction(amount, accountSource, accountDestination);
        Transaction expectedTransaction = transactionService
                .createTransaction(transaction)
                .statusCode(201)
                .as(Transaction.class);
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        List<Transaction> transactions = transactionService
                .getAllTransactions(today, tomorrow)
                .getJsonPath()
                .getList(".", Transaction.class);
        Transaction actualTransaction = transactions.stream().filter(t -> t.getId().equals(expectedTransaction.getId()))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Transaction is not found in list of transactions"));
        assertThat(actualTransaction.getSource().getBalance(), Matchers.comparesEqualTo(accountSource.withdraw(amount)));
        assertThat(actualTransaction.getDestination().getBalance(), Matchers.comparesEqualTo(accountDestination.deposit(amount)));
    }
}
