package ru.decease.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class BooksPage {

    public BooksPage open() {
        Selenide.open("/books");
        return this;
    }

    public BooksPage addBookToCollection(String bookTitle) {
        SelenideElement book = $$("span[role='gridcell']").findBy(text(bookTitle));
        book.sibling(0).$("button").click();
        confirm();
        return this;
    }
}
