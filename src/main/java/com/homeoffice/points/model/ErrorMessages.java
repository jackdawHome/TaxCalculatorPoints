package com.homeoffice.points.model;

import java.util.List;

public class ErrorMessages {
    private List<ErrorMessage> errors;

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<ErrorMessage> getErrors() {
        return List.copyOf(errors);
    }
}
