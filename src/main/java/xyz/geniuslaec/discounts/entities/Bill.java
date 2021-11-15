package xyz.geniuslaec.discounts.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Bill {
    private List<BillItem> items;

    public Bill(List<BillItem> items) {
        this.items = items;
    }

    public Bill() {
        this.items = new ArrayList<>();
    }

    public void addItem(BillItem item) {
        this.items.add(item);
    }

    public List<BillItem> getItems() {
        return this.items;
    }

    @JsonIgnore
    public Money getTotal() {
        if (items.isEmpty()) return new Money("BWP", BigDecimal.valueOf(0));

        String currency = items.get(0).getProduct().getPrice().getCurrency();
        var total = new Money(currency, BigDecimal.valueOf(0));

        for (BillItem billItem : items) {
            total = total.add(billItem.getTotal());
        }

        return total;
    }
}
