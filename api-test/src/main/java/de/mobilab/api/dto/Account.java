package de.mobilab.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import de.mobilab.api.currency.Currency;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class Account {

    private String id;
    private String owner;
    private BigDecimal balance;
    private Currency currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdOn;

    public Account(String owner, BigDecimal balance, Currency currency) {
        this.owner = owner;
        this.balance = balance;
        this.currency = currency;
    }

    public Account() {
    }

    public String getOwner() {
        return owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public BigDecimal withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
        return balance;
    }

    public BigDecimal deposit(BigDecimal amount) {
        balance = balance.add(amount);
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(id, account.id) &&
                Objects.equals(owner, account.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, owner);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", createdOn=" + createdOn +
                '}';
    }
}
