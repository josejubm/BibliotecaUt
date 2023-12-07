package com.biblotecaut.models;
import java.io.Serializable;

public class BookPdf implements Serializable {
    private String name;
    private Integer pdf;
    private Integer image;
    private String isbn;

    public BookPdf(String name, Integer pdf, Integer image, String isbn) {
        this.name = name;
        this.pdf = pdf;
        this.image = image;
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPdf() {
        return pdf;
    }

    public void setPdf(Integer pdf) {
        this.pdf = pdf;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}