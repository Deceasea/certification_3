package ru.decease.utils;

import io.qameta.allure.Description;
import io.restassured.http.ContentType;
import ru.decease.models.Book;

import static io.restassured.RestAssured.given;

public class APIService {
    String userName;
    String password;

    public APIService(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static void deleteAllBooks() {
        given()
                .auth()
                .preemptive()
                .basic(ConfigLoader.getUserName(), ConfigLoader.getPassword())
                .contentType(ContentType.JSON)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .delete(ConfigLoader.getBaseUrl() + "/BookStore/v1/Books?UserId=" + ConfigLoader.getUserId())
                .then()
                .statusCode(204);
    }

    @Description("Add specified number of books to user profile")
    public void addBooksToUserProfile(Book.BookCollection books) {
        given()
                .auth()
                .preemptive()
                .basic(ConfigLoader.getUserName(), ConfigLoader.getPassword())
                .contentType(ContentType.JSON)
                .body(books)
                .header("Accept", ContentType.JSON.getAcceptHeader())
                .post(ConfigLoader.getBaseUrl() + "/BookStore/v1/Books")
                .then()
                .statusCode(201);
    }
}
