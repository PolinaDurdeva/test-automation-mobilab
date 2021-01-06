package de.mobilab.ui.components;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

public class Notification {

    private final SelenideElement successMessage = $(".notification-success");
    private final SelenideElement errorMessage = $(".notification-error");

    public SelenideElement getSuccessMessage() {
        return successMessage;
    }

    public SelenideElement getErrorMessage() {
        return errorMessage;
    }



}
