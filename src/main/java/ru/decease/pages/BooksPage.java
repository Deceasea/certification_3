package ru.decease.pages;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BooksPage {
    private final WebDriver driver;

    public BooksPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Click on Book Store")
    public void clickOnBookStore() {
        WebElement menuItem = driver.findElements(By.id("item-2")).get(4);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView(true);", menuItem);
        menuItem.click();
    }
}