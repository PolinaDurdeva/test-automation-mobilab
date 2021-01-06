package de.mobilab.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonInclude(Include.NON_NULL)
public class Transaction {

    private String id;
    private BigDecimal amount;
    private Account source;
    private Account destination;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdOn;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, Account source, Account destination) {
        this.amount = amount;
        this.source = source;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Account getSource() {
        return source;
    }

    public Account getDestination() {
        return destination;
    }

    public Date getCreatedOn() {
        return createdOn;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", source=" + source +
                ", destination=" + destination +
                ", createdOn=" + createdOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(source, that.source) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(createdOn, that.createdOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, source, destination, createdOn);
    }
}