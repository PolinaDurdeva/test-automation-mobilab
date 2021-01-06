package de.mobilab.ui.components;

import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.Optional;

public class TableContent {

    private final ElementsCollection tableRows = $$("tbody tr");

    public Optional<ElementsCollection> findRowBySubstring(String substring) {
        return tableRows.stream()
                .filter(el -> el.has(Condition.text(substring)))
                .findFirst()
                .map(el -> el.$$("td"));
    }

    public ElementsCollection getRows() {
        return tableRows;
    }

}
