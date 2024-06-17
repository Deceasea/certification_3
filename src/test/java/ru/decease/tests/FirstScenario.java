package ru.decease.tests;

import io.qameta.allure.*;
import ru.decease.config.Config;
import ru.decease.pages.LoginPage;
import ru.decease.pages.ProfilePage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Login Tests")
@Feature("Login")
public class FirstScenario extends BaseTest {

    @Test
    @Story("User logs in and checks profile table")
    @Description("Login with valid credentials and check if profile table is empty")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginAndCheckEmptyProfileTable() {
        String username = Config.getProperty("username");
        String password = Config.getProperty("password");

        LoginPage loginPage = new LoginPage();
        ProfilePage profilePage = loginPage.open()
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();

        assertTrue(profilePage.isTableEmpty(), "Profile table is not empty.");
    }
}
