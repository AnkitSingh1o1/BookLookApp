package com.example.booklookapp;

import org.json.JSONArray;

import java.lang.reflect.Array;

public class ABook {

    private String bTitle;
    private String bAuthors;
    private String bLanguage;
    private String bPageCount;
    private String bPublisher;
    private String bCurrencyCode;
    private int bPrice;

    //Constructor
    public ABook(String title, String authors, String language, String pageCount,
                 String publisher, String currencyCode, int price) {
        bTitle = title;
        bAuthors = authors;
        bLanguage = language;
        bPageCount = pageCount;
        bPublisher = publisher;
        bCurrencyCode = currencyCode;
        bPrice = price;
    }


    public String getTitle() {
        return bTitle;
    }

    public String getAuthors() {
        return bAuthors;
    }

    public String getLanguageAndPageCount() {
        String languageAndPageCount = bLanguage + bPageCount;
        return languageAndPageCount;
    }

    public String getPublisher() {
        return bPublisher;
    }

    public String getCurrencyCodeAndPrice() {
        String amount = "";
        amount += (bPrice+bCurrencyCode);
        return amount;
    }

}
