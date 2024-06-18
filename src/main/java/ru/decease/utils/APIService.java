package ru.decease.utils;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import ru.decease.models.UserCredentials;

import java.util.Collection;

import static io.restassured.RestAssured.given;

public class APIService {
    private static String userId;
    private static String login;
    private static String password;

    public APIService(String login, String password) {
        APIService.login = login;
        APIService.password = password;
        fetchUserId();
    }

    private void fetchUserId() {
        userId = given()
                .body(new UserCredentials(login, password))
                .header("Accept", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .post(ConfigLoader.getBaseUrl() + "/Account/v1/Login")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .path("userId");
    }

    @Description("Add specified number of books to user profile")
    public static void addBooksToUserProfile(Collection<String> isbnList) {
        given()
                .auth()
                .preemptive()
                .basic(login, password)
                .body(new UserCredentials(userId, isbnList.toString()))
                .header("Accept", "*/*")
                .contentType(ContentType.JSON)
                .when()
                .post(ConfigLoader.getBaseUrl() + "/BookStore/v1/Books");
    }

    @Description("Delete all books from user profile")
    public void deleteAllBooksFromUserProfile() {
        given()
                .auth()
                .preemptive()
                .basic(login, password)
                .header("Accept", "*/*")
                .contentType(ContentType.JSON)
                .when()
                .delete(ConfigLoader.getBaseUrl() + "/BookStore/v1/Books?UserId=" + userId);
    }
}
