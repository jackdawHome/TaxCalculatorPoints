package com.homeoffice.points.model;

import java.math.BigDecimal;
import java.util.List;

public class TaxCalculationResult {
    private BigDecimal taxAmount;
    private BigDecimal effectiveTaxRate;
    private List<TaxPerBracket> taxPerBracket;

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public BigDecimal getEffectiveTaxRate() {
        return effectiveTaxRate;
    }

    public void setEffectiveTaxRate(BigDecimal effectiveTaxRate) {
        this.effectiveTaxRate = effectiveTaxRate;
    }

    public List<TaxPerBracket> getTaxPerBracket() {
        return List.copyOf(taxPerBracket);
    }

    public void setTaxPerBracket(List<TaxPerBracket> taxPerBracket) {
        this.taxPerBracket = taxPerBracket;
    }

    public TaxCalculationResult(BigDecimal taxAmount, BigDecimal effectiveTaxRate, List<TaxPerBracket> taxPerBracket) {
        this.taxAmount = taxAmount;
        this.effectiveTaxRate = effectiveTaxRate;
        this.taxPerBracket = taxPerBracket;
    }
}
