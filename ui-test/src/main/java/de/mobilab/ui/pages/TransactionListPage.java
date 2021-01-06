package de.mobilab.ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import de.mobilab.ui.components.TableContent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class TransactionListPage implements Page {

    private final SelenideElement dateFrom = $("#datefrom");
    private final SelenideElement dateTo = $("#dateto");
    private final SelenideElement searchButton = $("#transsearch");
    private final SelenideElement nextButton = $("li[title='next page']>a");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final TableContent tableContent = new TableContent();

    public TransactionListPage setDates(LocalDate from, LocalDate to){
        dateFrom.sendKeys(from.format(formatter));
        dateTo.sendKeys(to.format(formatter));
        searchButton.click();
        return this;
    }

    public TableContent getTable() {
        return tableContent;
    }

    public Optional<ElementsCollection> findTransaction(String transactionId) {
        boolean notScannedTable = true;
        while(notScannedTable) {
            Optional<ElementsCollection> foundRowValues = tableContent.findRowBySubstring(transactionId);
            if (foundRowValues.isPresent()) {
                return foundRowValues;
            }
            notScannedTable = clickNextPage();
        }
        return Optional.empty();
    }

    private boolean clickNextPage() {
        if( nextButton.isEnabled() ) {
            nextButton.click();
            return true;
        }
        return false;
    }

}
