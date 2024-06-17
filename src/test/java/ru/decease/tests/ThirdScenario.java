package ru.decease.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import ru.decease.config.Config;
import ru.decease.pages.BooksPage;
import ru.decease.pages.LoginPage;
import ru.decease.pages.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Books Tests")
@Feature("Books Management")
public class ThirdScenario extends BaseTest {

    @Test
    @Story("User adds and deletes books from collection")
    @Description("Login, add books to collection, delete all books, and check if collection is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddAndDeleteBooksInCollection() {
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
                .addBookToCollection("Learning JavaScript Design Patterns");

        ProfilePage profilePage = new ProfilePage();
        profilePage.open();
        assertEquals(2, profilePage.getBooksCount(), "The number of books in the collection is incorrect.");

        profilePage.deleteAllBooks();
        profilePage.open();
        assertTrue(profilePage.isTableEmpty(), "Profile table is not empty after deleting all books.");
    }
}
