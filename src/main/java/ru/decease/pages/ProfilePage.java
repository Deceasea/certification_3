package ru.decease.pages;

import io.qameta.allure.Description;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Description("Delete all books on the Profile page")
    public void deleteAllBooks() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        WebElement buttonDelete = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=\"Delete All Books\"]")));
        js.executeScript("arguments[0].scrollIntoView(true);", buttonDelete);
        buttonDelete.click();
        driver.findElement(By.cssSelector("#closeSmallModal-ok")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert2 = driver.switchTo().alert();
        alert2.accept();

    }

    @Description("Check if the table is empty on the Profile page")
    public String isTableEmpty() {
        return driver.findElement(By.cssSelector(".rt-noData")).getText();
    }

    public void setRow() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement buttonRow = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("span.select-wrap.-pageSizeOptions")));
        js.executeScript("arguments[0].scrollIntoView(true);", buttonRow);
        buttonRow.click();
        driver.findElement(By.cssSelector(".-pageSizeOptions > select > option:nth-child(2)")).click();
    }

    @Description("Get the number of books in the Profile")
    public List<WebElement> getBooksCount() {
        return driver.findElements(By.cssSelector(".rt-td img"));
    }
}
