package xyz.geniuslaec.discounts.services;

import org.springframework.stereotype.Service;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.services.contracts.DiscountService;
import xyz.geniuslaec.discounts.services.contracts.UserService;
import xyz.geniuslaec.discounts.util.DeductionUtil;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final UserService userService;

    public DiscountServiceImpl(
        UserService userService
    ) {
        this.userService = userService;
    }

    @Override
    public Money processDiscount(Bill bill, int userId) {
        var user = userService.getById(userId);

        var payableAfterPercentileDeduction = DeductionUtil.payableAfterPercentileDeduction(bill, user);
        return DeductionUtil.payableAfterValueDeduction(payableAfterPercentileDeduction);
    }
}
