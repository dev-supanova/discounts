package xyz.geniuslaec.discounts.services.contracts;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.Money;

public interface DiscountService {
    Money processDiscount(Bill bill, int userId);
}
