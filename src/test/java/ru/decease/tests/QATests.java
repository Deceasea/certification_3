package ru.decease.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.decease.models.Book;
import ru.decease.pages.BooksPage;
import ru.decease.pages.LoginPage;
import ru.decease.pages.ProfilePage;
import ru.decease.utils.APIService;
import ru.decease.utils.ConfigLoader;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Book Store")
@Owner("Irina Veselova")
@DisplayName("QA Testing Book Store")
public class QATests {

    // Fill the list via Postman
    private final Book firstBook = new Book("9781449325862");
    private final Book secondBook = new Book("9781449331818");
    private final Book thirdBook = new Book("9781449337711");
    private final Book fourthBook = new Book("9781449365035");
    private final Book fifthBook = new Book("9781491904244");
    private final Book sixthBook = new Book("9781491950296");
    private final Book.BookCollection twoBookCollection = new Book.BookCollection(new Book[]{thirdBook, fourthBook});
    private final Book.BookCollection sixBookCollection = new Book.BookCollection(new Book[]{firstBook, secondBook, thirdBook, fourthBook, fifthBook, sixthBook});
    private WebDriver driver;
    private LoginPage login;
    private ProfilePage profile;
    private BooksPage booksPage;
    private APIService apiService;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        login = new LoginPage(driver);
        profile = new ProfilePage(driver);
        booksPage = new BooksPage(driver);
        apiService = new APIService(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterEach
    public void quit() {
        APIService.deleteAllBooks();
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Login and check the profile")
    @Description("Verify that the profile is empty after successful login")
    @Tag("Scenario_1")
    @Severity(SeverityLevel.BLOCKER)
    public void profileTest() {
        login.openPage();
        login.loginStore(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        assertEquals("No rows found",
                profile.isTableEmpty(),
                "Expected profile have no books, but found some.");
    }

    @Test
    @DisplayName("Add 6 books to the profile")
    @Description("Verify that added books are displayed in the user profile")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Scenario_2")
    public void displayAddedBooksTest() {
        login.openPage();
        login.loginStore(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        booksPage.clickOnBookStore();
        apiService.addBooksToUserProfile(new Book.BookCollection(sixBookCollection.getCollectionOfIsbns()));
        login.openPage();
        login.loginStore(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        profile.setRow();
        assertEquals(ConfigLoader.expectedCountBook(), profile.getBooksCount().size(),
                "Expected and displayed book counts not match."
        );
    }

    @Test
    @DisplayName("Successful add and delete books from profile")
    @Description("Verify adding and then deleting books from the user profile")
    @Severity(SeverityLevel.CRITICAL)
    @Tag("Scenario_3")
    public void addAndDeleteBooksTest() {
        login.openPage();
        login.loginStore(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        booksPage.clickOnBookStore();
        apiService.addBooksToUserProfile(new Book.BookCollection(twoBookCollection.getCollectionOfIsbns()));
        login.openPage();
        login.loginStore(ConfigLoader.getUserName(), ConfigLoader.getPassword());
        assertEquals(ConfigLoader.expectedCountBookAddAndDelete(),
                profile.getBooksCount().size(),
                "Expected and displayed book counts not match.");
        profile.deleteAllBooks();
        assertEquals(ConfigLoader.countBookInNewProfile(),
                profile.getBooksCount().size(),
                "Expected profile to have no books after delete");
    }
}
