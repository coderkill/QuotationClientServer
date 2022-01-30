package util;

import pojo.Quotation;

import java.util.Random;

public class QuotationGenerator {

    public static Quotation returnQuotaion(String symbol) {
        double open = randomDouble(50, 120);
        double high = open + randomDouble(0,5);
        double low = open - randomDouble(0,5);
        double ask = high - randomDouble(0,1);
        double bid = low + randomDouble(0,1);
        return new Quotation(symbol, open, high, low, bid, ask);
    }

    public static double randomDouble(int low, int high) {
        return low + new Random().nextDouble() * (high - low);
    }
}
