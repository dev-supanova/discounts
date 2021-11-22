package xyz.geniuslaec.discounts.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class MonetTests {
    @Test
    void correctToString() {
        var money = new Money("BWP", BigDecimal.valueOf(20.50));
        var strMoney = money.toString();

        assertEquals("BWP20.50", strMoney);
    }

    @Test
    void canAddMoney() {
        var money = new Money("BWP", BigDecimal.valueOf(20.50));
        var sum = money.add(new Money("BWP", BigDecimal.valueOf(25.50)));

        assertEquals(BigDecimal.valueOf(46.00), sum.getAmount());
    }

    @Test
    void canSubtractMoney() {
        var money = new Money("BWP", BigDecimal.valueOf(20.50));
        var diff = money.subtract(new Money("BWP", BigDecimal.valueOf(10.50)));

        assertEquals(BigDecimal.valueOf(10.00), diff.getAmount());
    }
}
