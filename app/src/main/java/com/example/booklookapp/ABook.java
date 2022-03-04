package com.example.booklookapp;

public class ABook {

    private String bTitle;
    private String bAuthors;
    private String bLanguage;
    private String bPageCount;
    private String bSaleability;
    private String bThumbnailURL;
    private String bPublisher;

    //Constructor
    public ABook(String title, String authors, String language, String pageCount, String saleability,String publisher,
                 String thumbnail) {
        bTitle = title;
        bAuthors = authors;
        bLanguage = language;
        bPageCount = pageCount;
        bSaleability = saleability;
        bPublisher = publisher;
        bThumbnailURL = thumbnail;
    }


    public String getTitle() {
        return bTitle;
    }

    public String getAuthors() {
        return bAuthors;
    }

    public String getLanguageAndPageCount() {
        String languageAndPageCount = bLanguage + "\n\n" + bPageCount + " Pages";
        return languageAndPageCount;
    }

    public String getPublisher() {
        return bPublisher;
    }

    public String getSaleability() {
        return bSaleability;
    }

    public String getThumbnail() {
        return bThumbnailURL;
    }
}