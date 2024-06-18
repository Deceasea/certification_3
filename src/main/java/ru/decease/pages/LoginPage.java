package ru.decease.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Open the login page")
    public LoginPage open() {
        Selenide.open("/login");
        return this;
    }

    @Description("Enter username in the \"UserName\" field")
    public LoginPage enterUsername(String username) {
        $("#UserName").setValue(username);
        return this;
    }

    @Description("Enter password in the \"Password\" field")
    public LoginPage enterPassword(String password) {
        $("#Password").setValue(password);
        return this;
    }

    @Description("Click the \"Login\" button and attach a screenshot")
    public ProfilePage clickLoginButton() {
        $("#login").click();
        attachScreenshot("Login");
        return page(ProfilePage.class);
    }

    private byte[] attachScreenshot(String name) {
        byte[] screenshot = Selenide.screenshot(name).getBytes();
        Allure.addAttachment(name, "image/png", Arrays.toString(screenshot));
        return screenshot;
    }
}
