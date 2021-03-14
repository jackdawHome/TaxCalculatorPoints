package com.homeoffice.points.model;

public class WebResponse {
    private int returnCode;
    private String body;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public WebResponse(int returnCode, String body) {
        this.returnCode = returnCode;
        this.body = body;
    }
}
