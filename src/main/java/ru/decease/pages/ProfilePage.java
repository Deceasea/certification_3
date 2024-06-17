package ru.decease.pages;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {

    public ProfilePage open() {
        Selenide.open("/profile");
        return this;
    }

    public boolean isTableEmpty() {
        return $$(".rt-tr-group").isEmpty();
    }

    public int getBooksCount() {
        return $$(".rt-tr-group").size();
    }

    public ProfilePage deleteAllBooks() {
        $("#submit").click();
        confirm();
        return this;
    }
}
