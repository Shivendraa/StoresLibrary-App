package com.user.storeslibrary;

public class UserHelperClass {

    private String bookName, bookPrice, bookCategory, bookUploader;

    UserHelperClass(){

    }

    public UserHelperClass(String bookName, String bookPrice, String bookCategory, String bookUploader) {
        this.bookName = bookName;
        this.bookPrice = bookPrice;
        this.bookCategory = bookCategory;
        this.bookUploader = bookUploader;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookUploader() {
        return bookUploader;
    }

    public void setBookUploader(String bookUploader) {
        this.bookUploader = bookUploader;
    }
}
