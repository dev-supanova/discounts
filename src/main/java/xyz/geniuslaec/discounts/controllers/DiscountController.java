package xyz.geniuslaec.discounts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.services.contracts.DiscountService;

@RestController
@RequestMapping(path = "/discounts")
public class DiscountController {
    private final DiscountService discountService;

    public DiscountController(
        DiscountService discountService
    ) {
        this.discountService = discountService;
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Money> process(@RequestBody Bill bill, @PathVariable("userId") int userId) {
        if (bill.getItems().isEmpty()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(discountService.processDiscount(bill, userId));
    }
}
