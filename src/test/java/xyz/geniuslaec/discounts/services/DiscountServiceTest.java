package xyz.geniuslaec.discounts.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.BillItem;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.entities.Product;
import xyz.geniuslaec.discounts.entities.ProductType;
import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;
import xyz.geniuslaec.discounts.services.contracts.DiscountService;
import xyz.geniuslaec.discounts.services.contracts.UserService;

@ExtendWith(MockitoExtension.class)
public class DiscountServiceTest {
    User user = new User(0, UserType.EMPLOYEE, new Date());
    DiscountService discountService;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        discountService = new DiscountServiceImpl(userService);
    }

    @Test
    void processDiscountCorreclty() {
        User user = new User(0, UserType.EMPLOYEE, new Date());
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(200)), ProductType.OTHER), 2));
        var bill = new Bill(items);

        when(userService.getById(0)).thenReturn(user);

        var actual = discountService.processDiscount(bill, 0);
        var expected = new Money("BWP", BigDecimal.valueOf(270));

        assertEquals(expected.getAmount().longValue(), actual.getAmount().longValue());
        assertEquals(expected.getCurrency(), actual.getCurrency());
    }
}
