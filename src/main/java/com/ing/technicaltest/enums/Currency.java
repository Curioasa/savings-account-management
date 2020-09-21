package com.ing.technicaltest.enums;

public enum Currency {
    RON("RON"),
    EUR("EUR"),
    GBP("GBP");

    private String curencyName;

    Currency(String curencyName) {
        this.curencyName = curencyName;
    }

    public String getCurencyName() {
        return curencyName;
    }
}
