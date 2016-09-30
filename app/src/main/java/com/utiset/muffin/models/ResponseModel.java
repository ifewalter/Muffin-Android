package com.utiset.muffin.models;

/**
 * Created by ife on 03/06/16.
 */
public class ResponseModel {

    private int statusCode;
    private String responseBody;


    public ResponseModel(int _statusCode, String _responseBody)
    {
        setStatusCode(_statusCode);
        setResponseBody(_responseBody);
    }

    public String getResponseBody() {
        return responseBody.toString();
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
