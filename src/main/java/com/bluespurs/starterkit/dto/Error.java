package com.bluespurs.starterkit.dto;

/**
 * Created by dmytr on 2017-04-13.
 */
public class Error {
    private String error;

    public Error() {
    }

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
