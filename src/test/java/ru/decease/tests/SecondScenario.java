package ru.decease.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import ru.decease.config.Config;
import ru.decease.pages.BooksPage;
import ru.decease.pages.LoginPage;
import ru.decease.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Books Tests")
@Feature("Books Management")
public class SecondScenario extends BaseTest {

    @Test
    @Story("User adds books to collection")
    @Description("Login and add books to collection, then check if they are correctly added")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddBooksToCollection() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        LoginPage loginPage = new LoginPage();
        loginPage.open()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        BooksPage booksPage = new BooksPage();
        booksPage.open()
                .addBookToCollection("Git Pocket Guide")
                .addBookToCollection("Learning JavaScript Design Patterns")
                .addBookToCollection("Designing Evolvable Web APIs with ASP.NET")
                .addBookToCollection("Speaking JavaScript")
                .addBookToCollection("You Don't Know JS")
                .addBookToCollection("Programming JavaScript Applications");

        ProfilePage profilePage = new ProfilePage();
        profilePage.open();

        assertEquals(6, profilePage.getBooksCount(), "The number of books in the collection is incorrect.");
    }
}
