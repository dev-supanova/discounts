package xyz.geniuslaec.discounts.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Money {
    private String currency;
    private BigDecimal amount;
    
    public Money(String currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public Money() {}

    @Override
    public String toString() {
        return currency + amount.setScale(2, RoundingMode.HALF_EVEN);
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }

        return new Money(this.currency, this.amount.add(other.amount));
    }

    public Money subtract(Money other) {
        if (!other.currency.equals(this.currency)) {
            throw new IllegalArgumentException("Cannot add different currencies");
        }

        if (this.amount.compareTo(other.amount) < 0) {
            throw new IllegalArgumentException("We do not allow negative after discount");
        }

        return new Money(this.currency, this.amount.subtract(other.amount));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((amount == null) ? 0 : amount.hashCode());
        result = prime * result + ((currency == null) ? 0 : currency.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Money other = (Money) obj;
        if (amount == null) {
            if (other.amount != null)
                return false;
        } else if (!amount.equals(other.amount))
            return false;
        if (currency == null) {
            if (other.currency != null)
                return false;
        } else if (!currency.equals(other.currency))
            return false;
        return true;
    }

    
}
