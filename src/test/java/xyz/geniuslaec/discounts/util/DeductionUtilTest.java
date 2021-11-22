package xyz.geniuslaec.discounts.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.BillItem;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.entities.Product;
import xyz.geniuslaec.discounts.entities.ProductType;
import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;

public class DeductionUtilTest {
    @Test
    public void calculatesCorrectDiscount() {
        int percent = 10;
        var item = new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 2);
        var expected = new Money("BWP", BigDecimal.valueOf(20));

        var result = DeductionUtil.getDiscount(percent, item);

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void calculatesCorrectDiscountOnGrocery() {
        int percent = 10;
        var item = new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.GROCERY), 2);
        var expected = new Money("BWP", BigDecimal.valueOf(0));

        var result = DeductionUtil.getDiscount(percent, item);

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void correctPayableAfterValueDeduction() {
        var result = DeductionUtil.payableAfterValueDeduction(new Money("BWP", BigDecimal.valueOf(900)));
        var expected = new Money("BWP", BigDecimal.valueOf(855));

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void correctPayableAfterPercentileDeductionEmployee() {
        User user = new User(0, UserType.EMPLOYEE, new Date());
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 1));
        var bill = new Bill(items);

        var result = DeductionUtil.payableAfterPercentileDeduction(bill, user);
        var expected = new Money("BWP", BigDecimal.valueOf(70));

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void correctPayableAfterPercentileDeductionEmployeeMulti() {
        User user = new User(0, UserType.EMPLOYEE, new Date());
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 1));
        items.add(new BillItem(new Product("Two", new Money("BWP", BigDecimal.valueOf(200)), ProductType.OTHER), 2));

        var bill = new Bill(items);

        var result = DeductionUtil.payableAfterPercentileDeduction(bill, user);
        var expected = new Money("BWP", BigDecimal.valueOf(350));

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void correctPayableAfterPercentileDeductionAffiliate() {
        User user = new User(0, UserType.AFFILIATE, new Date());
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 1));
        var bill = new Bill(items);

        var result = DeductionUtil.payableAfterPercentileDeduction(bill, user);
        var expected = new Money("BWP", BigDecimal.valueOf(90));

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }

    @Test
    public void correctPayableAfterPercentileDeductionLongServingCustomer() {
        var date = new GregorianCalendar(2015, Calendar.AUGUST, 22).getTime();
        User user = new User(0, UserType.OTHER, date);
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 1));
        var bill = new Bill(items);

        var result = DeductionUtil.payableAfterPercentileDeduction(bill, user);
        var expected = new Money("BWP", BigDecimal.valueOf(95));

        assertEquals(expected.getAmount().longValue(), result.getAmount().longValue());
        assertEquals(expected.getCurrency(), result.getCurrency());
    }
}
