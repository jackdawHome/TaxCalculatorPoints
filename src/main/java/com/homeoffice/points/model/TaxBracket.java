package com.homeoffice.points.model;

import java.math.BigDecimal;

public class TaxBracket {
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal rate;

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min.setScale(2);
    }
    // Assigns Double.MAX_VALUE if max is null (sure it is overkill for salary)
    public BigDecimal getMax() {
        return max == null ? BigDecimal.valueOf(Double.MAX_VALUE) : max;
    }

    public void setMax(BigDecimal max) {
        this.max = max.setScale(2);
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public TaxBracket() {
    }

    public TaxBracket(BigDecimal min, BigDecimal max, BigDecimal rate) {
        this.min = min;
        this.max = max;
        this.rate = rate;
    }

    public String toString() {
        //return String.format("[%.2f - %.2f]",min,max == null ? 0.0 : max);
        return String.format("[%s - %s]",min.toString(),max == null ? "and up" : max.toString());
    }


}
