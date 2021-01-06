package de.mobilab.api.dataprovider;

import de.mobilab.api.currency.Currency;
import de.mobilab.api.dto.Account;
import de.mobilab.api.services.AccountService;
import java.math.BigDecimal;

public class AccountDataProvider {

    private final AccountService accountService;

    public AccountDataProvider() {
        accountService = new AccountService();
    }

    public Account createAccount(String owner, BigDecimal balance, Currency currency) {
        Account account = new Account(owner, balance, currency);
        return accountService.createAccount(account).statusCode(201).as(Account.class);
    }

}
