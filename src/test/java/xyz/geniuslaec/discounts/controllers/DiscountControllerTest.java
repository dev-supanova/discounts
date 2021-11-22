package xyz.geniuslaec.discounts.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import xyz.geniuslaec.discounts.entities.Bill;
import xyz.geniuslaec.discounts.entities.BillItem;
import xyz.geniuslaec.discounts.entities.Money;
import xyz.geniuslaec.discounts.entities.Product;
import xyz.geniuslaec.discounts.entities.ProductType;
import xyz.geniuslaec.discounts.entities.User;
import xyz.geniuslaec.discounts.entities.UserType;
import xyz.geniuslaec.discounts.services.contracts.DiscountService;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {
    @MockBean
    DiscountService discountService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Bill> billRequest;

    @Test
    void canCalculateDiscount() throws Exception {
        User user = new User(0, UserType.EMPLOYEE, new Date());
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem(new Product("One", new Money("BWP", BigDecimal.valueOf(100)), ProductType.OTHER), 1));
        var bill = new Bill(items);

        Mockito.when(discountService.processDiscount(bill, user.getId())).thenReturn(new Money());

        var response = mockMvc.perform(post("/discounts/0").contentType(MediaType.APPLICATION_JSON).content(billRequest.write(bill).getJson())).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void willReturnBadRequestOnEmptyBill() throws Exception {
        User user = new User(0, UserType.EMPLOYEE, new Date());
        List<BillItem> items = new ArrayList<>();
        var bill = new Bill(items);

        Mockito.when(discountService.processDiscount(bill, user.getId())).thenReturn(new Money());

        var response = mockMvc.perform(post("/discounts/0").contentType(MediaType.APPLICATION_JSON).content(billRequest.write(bill).getJson())).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }
}
