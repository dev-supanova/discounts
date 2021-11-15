package xyz.geniuslaec.discounts.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class DateUtilTests {

    @Test
    public void getCorrectDifference() {
        var date = new Date();

        assertNotNull(DateUtil.dateToLocalDate(date));
        assertTrue(DateUtil.dateToLocalDate(date) instanceof LocalDate);
    }
}
