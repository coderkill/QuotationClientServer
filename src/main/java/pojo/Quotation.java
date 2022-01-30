package pojo;

import java.io.Serializable;

public class Quotation implements Serializable {
    private String symbol;
    private double open;
    private double high;
    private double low;
    private double bid;
    private double ask;

    public Quotation(String symbol, double open, double high, double low, double bid, double ask) {
        this.symbol = symbol;
        this.open = open;
        this.high = high;
        this.low = low;
        this.bid = bid;
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "Quotation{" + "symbol='" + symbol + '\'' + ", open=" + open + ", high=" + high + ", low=" + low + ", bid=" + bid + ", ask=" + ask + '}';
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }
}
