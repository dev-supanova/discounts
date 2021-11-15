package xyz.geniuslaec.discounts.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.BillItem;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.entities.ProductType;
import xyz.geniuslaec.discounts.entities.User;

public interface DeductionUtil {
    static Money payableAfterPercentileDeduction(Bill bill, User user) {
        int percentage = 0;

        switch (user.getUserType()) {
            case EMPLOYEE:
                percentage = 30;
                break;
            case AFFILIATE:
                percentage = 10;
                break;
            case OTHER:
            default:
                var period = Period.between(DateUtil.dateToLocalDate(user.getCustomerSince()), LocalDate.now());
                percentage = period.getYears() >= 2 ? 5 : 0;
        }

        Money deduction = null;

        for (var item : bill.getItems()) {
            var discount = DeductionUtil.getDiscount(percentage, item);

            if (deduction == null) {
                deduction = discount;
            } else {
                deduction = deduction.add(discount);
            }
        }

        return bill.getTotal().subtract(deduction);
    }

    static Money payableAfterValueDeduction(Money total) {
        var fullCount = total.getAmount().divide(BigDecimal.valueOf(100)).setScale(0, RoundingMode.DOWN);
        var deductible = fullCount.multiply(BigDecimal.valueOf(5));

        return total.subtract(new Money(total.getCurrency(), deductible));
    }

    static Money getDiscount(int percent, BillItem item) {

        if (item.getProduct().getProductType() == ProductType.GROCERY) {
            return new Money(item.getProduct().getPrice().getCurrency(), BigDecimal.valueOf(0));
        }

        var amount = item.getTotal().getAmount().multiply(BigDecimal.valueOf(((double)percent)/100));
        return new Money(item.getProduct().getPrice().getCurrency(), amount);
    }
}
