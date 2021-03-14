package com.homeoffice.points.model;

import java.math.BigDecimal;

public class TaxPerBracket {
    private TaxBracket bracket;
    private BigDecimal bracketTaxPortion;

    public TaxBracket getBracket() {
        return bracket;
    }

    public void setBracket(TaxBracket bracket) {
        this.bracket = bracket;
    }

    public BigDecimal getBracketTaxPortion() {
        return bracketTaxPortion;
    }

    public void setBracketTaxPortion(BigDecimal bracketTaxPortion) {
        this.bracketTaxPortion = bracketTaxPortion;
    }

    public TaxPerBracket(TaxBracket bracket, BigDecimal bracketTaxPortion) {
        this.bracket = bracket;
        this.bracketTaxPortion = bracketTaxPortion;
    }

    public String toString() {
        return String.format("Tax = %.2f %s", bracketTaxPortion, bracket.toString());
    }
}
