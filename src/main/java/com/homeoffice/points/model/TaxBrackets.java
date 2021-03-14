package com.homeoffice.points.model;
import java.util.List;

public class TaxBrackets {
    private List<TaxBracket> tax_brackets;

    public List<TaxBracket> getTax_brackets() {
        return List.copyOf(tax_brackets);
    }

    public void setTax_brackets(List<TaxBracket> tax_brackets) {
        this.tax_brackets = tax_brackets;
    }
}
