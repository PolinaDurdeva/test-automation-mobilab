package de.mobilab.ui.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import de.mobilab.ui.components.TableContent;
import java.util.Optional;

public class AccountListPage implements Page {

    private final SelenideElement nextButton = $("#pagNext");

    public Optional<ElementsCollection> findAccount(String accountId) {
        TableContent tableContent = new TableContent();
        boolean notScannedTable = true;
        while(notScannedTable) {
            Optional<ElementsCollection> foundRowValues = tableContent.findRowBySubstring(accountId);
            if (foundRowValues.isPresent()) {
                return foundRowValues;
            }
            notScannedTable = clickNextPage();
            if (notScannedTable) {
                Selenide.sleep(2000);
            }
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
