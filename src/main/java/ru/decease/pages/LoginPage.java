package ru.decease.pages;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Open the login page")
    public void openPage() {
        this.driver.get("https://demoqa.com/login");
    }

    @Description("Login")
    public void loginStore(String user, String password) {
        enterUser(user);
        enterPassword(password);
        clickLoginButton();
    }

    @Description("Enter username in the UserName field")
    public void enterUser(String user) {

        driver.findElement(By.cssSelector("#userName")).sendKeys(user);
    }

    @Description("Enter password in the Password field")
    public void enterPassword(String password) {
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
    }

    @Description("Click the Login button and attach a screenshot")
    public void clickLoginButton() {
        driver.findElement(By.cssSelector("#login")).click();
        attachScreenshot();
    }

    private byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
