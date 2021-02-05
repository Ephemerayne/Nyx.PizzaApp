package space.lala.nyxpizzaapp.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class PriceFormatter {
    private PriceFormatter() {
    }

    public static String format(double price) {
        NumberFormat formatterPrice = new DecimalFormat("#0.00");
        return formatterPrice.format(price);
    }
}
