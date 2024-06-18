package ru.decease.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.decease.models.UserCredentials;
import ru.decease.pages.BooksPage;
import ru.decease.pages.LoginPage;
import ru.decease.pages.ProfilePage;
import ru.decease.utils.APIService;
import ru.decease.utils.ConfigLoader;

import java.time.Duration;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Book Store")
@Owner("Irina Veselova")
@DisplayName("QA Testing Book Store")
public class QATests {

    private WebDriver driver;
    private UserCredentials userCredentials;
    private LoginPage login;
    private ProfilePage profile;
    private BooksPage booksPage;
    private BooksPage menu;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        login = new LoginPage();
        profile = new ProfilePage();
        booksPage = new BooksPage();
        menu = new BooksPage();
        userCredentials = new UserCredentials(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void cleanup() {
        if (driver != null) {
            ProfilePage.deleteAllBooks();
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login and check the profile")
    @Description("Verify that the profile is empty after successful login")
    @Tag("Scenario 1")
    @Severity(SeverityLevel.CRITICAL)
    public void profileTest() {
        login.open();
        login.enterUsername(ConfigLoader.getUserName());
        login.enterPassword(ConfigLoader.getPassword());
        login.clickLoginButton();

        step("Check that the profile has no books", () -> {
            assertEquals(ConfigLoader.countBookInNewProfile(),
                    profile.getBooksCount(),
                    "Expected profile to have no books, but found some."
            );
        });
    }

    @Test
    @DisplayName("Add 6 books in profile")
    @Description("Verify that added books are displayed in the user profile")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Scenario 2")
    public void displayAddedBooksTest() {
        login.open();
        login.enterUsername(ConfigLoader.getUserName());
        login.enterPassword(ConfigLoader.getPassword());
        login.clickLoginButton();
        menu.clickOnBookStore();

        APIService.addBooksToUserProfile(booksPage.getAllIsbn(ConfigLoader.countBookAdd()));

        login.open();
        login.enterUsername(ConfigLoader.getUserName());
        login.enterPassword(ConfigLoader.getPassword());
        login.clickLoginButton();

        step("Verify expected and displayed book count match", () -> {
            assertEquals(ConfigLoader.expectedCountBook(),
                    profile.getBooksCount(),
                    "Expected and displayed book counts not match."
            );
        });
    }

    @Test
    @DisplayName("Successful add and delete books from profile")
    @Description("Verify adding and then deleting books from the user profile")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Scenario 3")
    public void addAndDeleteBooksTest() {
        login.open();
        login.enterUsername(ConfigLoader.getUserName());
        login.enterPassword(ConfigLoader.getPassword());
        login.clickLoginButton();
        menu.clickOnBookStore();

        APIService.addBooksToUserProfile(booksPage.getAllIsbn(ConfigLoader.countBookAddAndDelete()));

        login.open();
        login.enterUsername(ConfigLoader.getUserName());
        login.enterPassword(ConfigLoader.getPassword());
        login.clickLoginButton();

        step("Verify expected and displayed book count match", () -> {
            assertEquals(ConfigLoader.expectedCountBookAddAndDelete(),
                    profile.getBooksCount(),
                    "Expected and displayed book counts not match."
            );
        });

        ProfilePage.deleteAllBooks();

        step("Verify no books in profile after deletion", () -> {
            assertEquals(ConfigLoader.countBookInNewProfile(),
                    profile.getBooksCount(),
                    "Expected profile to have no books after delete"
            );
        });
    }
}
