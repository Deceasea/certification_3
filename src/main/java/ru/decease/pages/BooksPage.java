package ru.decease.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BooksPage {

    private WebDriver driver;

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Add books to the collection")
    public void addBooksToCollection(int count) {
        for (int i = 0; i < count; i++) {
            SelenideElement bookElement = $$(".rt-tr-group").get(i);
            bookElement.$(".action-buttons button").click();
            confirmAlert();
        }
    }

    private void confirmAlert() {
        switchTo().alert().accept();
    }

    @Description("Get specified number of books")
    public Collection<String> getAllIsbn(int countOfBooks) {
        List<String> resultIsbn = new ArrayList<>();

        for (int i = 0; i < countOfBooks; i++) {
            String isbn = $$(".rt-tr-group").get(i).$("a").getAttribute("href").split("=")[1];
            resultIsbn.add(isbn);
        }

        return resultIsbn;
    }

    @Description("Click on Book Store")
    public void clickOnBookStore() {
        SelenideElement menuItem = $$(".btn.btn-light").get(30);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", menuItem);
        menuItem.click();
    }
}
