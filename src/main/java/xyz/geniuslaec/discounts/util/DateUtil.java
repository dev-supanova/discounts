package xyz.geniuslaec.discounts.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public interface DateUtil {
    static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
