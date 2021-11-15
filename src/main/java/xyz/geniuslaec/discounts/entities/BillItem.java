package xyz.geniuslaec.discounts.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BillItem {
    private Product product;
    private int quantity;

    public BillItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BillItem() {
        product = new Product();
        quantity = 0;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @JsonIgnore
    public Money getTotal() {
        return new Money(
            product.getPrice().getCurrency(), 
            product.getPrice().getAmount().multiply(BigDecimal.valueOf(quantity)));
    }
}
