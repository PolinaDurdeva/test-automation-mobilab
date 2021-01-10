package de.mobilab.ui.tests;

import com.codeborne.selenide.CollectionCondition;
import de.mobilab.api.currency.Currency;
import de.mobilab.api.dataprovider.AccountDataProvider;
import de.mobilab.api.dto.Account;
import de.mobilab.api.dto.Transaction;
import de.mobilab.api.services.TransactionService;
import de.mobilab.ui.pages.MainPage;
import io.qameta.allure.Description;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TransactionListTest extends BaseTest {

    private Transaction transaction;

    @BeforeClass
    public void createTwoAccount() {
        TransactionService transactionService = new TransactionService();
        AccountDataProvider accountDataProvider = new AccountDataProvider();
        Account accountSource = accountDataProvider.createAccount("A", BigDecimal.valueOf(100.1), Currency.EURO);
        Account accountDestination = accountDataProvider.createAccount("B", BigDecimal.valueOf(137.7), Currency.EURO);
        Transaction tran = new Transaction(BigDecimal.valueOf(88.8), accountSource, accountDestination);
        transaction = transactionService.createTransaction(tran).statusCode(201).as(Transaction.class);
    }

    @Test
    @Description("Transaction list page with default parameters displays transactions")
    public void createdTransactionDisplayedInTableWithDefaultDates() {
        MainPage.open()
                .goToTransactionList()
                .getTable()
                .getRows()
                .shouldHave(CollectionCondition.sizeGreaterThan(0));
    }

    @Test
    @Description("New transactions appears in the transaction list with correct data")
    public void createdTransactionDisplayedInTable() {
        final LocalDate today = LocalDate.now();
        final LocalDate tomorrow = LocalDate.now().plusDays(1);
        MainPage.open()
                .goToTransactionList()
                .setDates(today, tomorrow)
                .findTransaction(transaction.getId())
                .orElseThrow(() -> new AssertionError("Transaction #id "+ transaction.getId() + " is not found"))
                .shouldHave(CollectionCondition.texts(
                        transaction.getId(),
                        transaction.getSource().getOwner(),
                        transaction.getSource().getBalance().stripTrailingZeros().toString(),
                        transaction.getSource().getCurrency().name(),
                        transaction.getDestination().getOwner(),
                        transaction.getDestination().getBalance().stripTrailingZeros().toString(),
                        transaction.getDestination().getCurrency().name(),
                        transaction.getAmount().toString(),
                        ""
                ));

    }

}
