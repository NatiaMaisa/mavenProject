package org.example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class RestAssuredClass {

    @Test
    public void restApi() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
                 given()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void restApi1() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
                 given()
                .pathParam("postId", 1) // გვინდა პოსტს ID ით 1 მიაკითხოს
                .when()
                .get("/posts/{postId}")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void restApi2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
                 given()
                .queryParam("postId", 1)
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void restApi3() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
                 given()
                .when()
                .get("/comments")
                .then()
                .statusCode(200)
                .time(lessThan(1000L))
                .log().body();
    }
    // Bearer Authorization
    @Test
    public void restApi4() {
        RestAssured.baseURI = "https://api.automation.ge/index.php"; //დოკუმენტაცია https://api.automation.ge/doc/
                 given()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IlRlc3QgVXNlciIsImlhdCI6MTUxNjIzOTAyMn0.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c" )
                .when()
                .get()
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .log().body();
    }
    // Basic Authorization
    @Test
    public void restApi5() {
        RestAssured.baseURI = "https://api.automation.ge/index.php"; //დოკუმენტაცია https://api.automation.ge/doc/
                 given()
                .auth()
                .preemptive()
                .basic("test_user", "test_password")
                .when()
                .get()
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .log().body();
    }

    @Test
    public void restApi6() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

              Response response =
                          given()
                                  .pathParam("id", 1)
                         .when()
                         .get("/posts/{id}")
                         .then()
                         .extract()
                         .response();
        System.out.println(response.asString()); // გარდაქმნა string-ად, რომ დაგვიბეჭდოს response
        //Assert.assertTrue(response.asString().contains("userId"));
    }
    @Test
    public void restApi7() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        List<String> response =
                given()
                        .when()
                        .get("/posts")
                        .then()
                        .extract()
                        .response()
                        .path("title");
        System.out.println(response.get(1));

       for (String title : response) {
            System.out.println(title);
        }

    }


    @Test
    public void restApi8() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String requestBody = "{\n" +
                " \"title\": \"foo\", \n" +
                " \"body\": \"bar\", \n" +
                " \"userId\": 1\n" +
                "}";

        given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .log().all();
    }



}