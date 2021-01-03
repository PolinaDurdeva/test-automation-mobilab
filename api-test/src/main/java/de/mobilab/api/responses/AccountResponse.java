package de.mobilab.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.mobilab.api.currency.Currency;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class AccountResponse {

    private String id;
    private String owner;
    private BigDecimal balance;
    private Currency currency;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdOn;

    public String getId() {
        return id;
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

    public Date getCreatedOn() {
        return createdOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountResponse that = (AccountResponse) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AccountResponse{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                ", currency=" + currency +
                ", createdOn=" + createdOn +
                '}';
    }
}
