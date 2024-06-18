package ru.decease.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfilePage {

    private WebDriver driver;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Description("Check if the table is empty on the Profile page")
    public boolean isTableEmpty() {
        return $("#profile .rt-noData").exists();
    }

    @Description("Get the number of books in the Profile")
    public int getBooksCount() {
        return $$("#profile .rt-tr-group").size();
    }

    @Description("Delete all books on the Profile page")
    public static void deleteAllBooks() {
        $("#delete-record-undefined").click();
        $("#closeSmallModal-ok").click();
    }

    @Attachment(value = "ScreenShot.png", fileExtension = ".png", type = "image/png")
    private byte[] getPageScreen() {
        return Selenide.screenshot(OutputType.BYTES);
    }
}
