package tests;

import io.restassured.RestAssured;
import models.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LoginExtendedTests {
    @BeforeAll
    public static void setupRestAssured() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    void loginSuccessful() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = step("Отправка запроса на аутентификацию и получение токена", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(authData)
                        .contentType(JSON)

                        .when()
                        .post("/login")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(LoginResponseModel.class));

        step("Проверка токена", () ->
                assertNotNull(response.getToken(), "Token should not be null"));
    }

    @Test
    void loginUnSuccessful() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("peter@klaven");

        LoginResponseModel response = step("Отправка запроса на аутентификацию с неверными данными", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(authData)
                        .contentType(JSON)

                        .when()
                        .post("/login")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(400)
                        .extract().as(LoginResponseModel.class));

        step("Проверка сообщения об ошибке", () ->
                assertEquals("Missing password", response.getError(), "Error message should be 'Missing password'"));
    }

    @Test
    void createUsers() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        step("Создание пользователя", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(userData)
                        .contentType(JSON)

                        .when()
                        .post("/users")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(201)
                        .body("name", is("morpheus"))
                        .body("job", is("leader")));
    }

    @Test
    void updateUsers() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        step("Обновление пользователя", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(userData)
                        .contentType(JSON)

                        .when()
                        .put("/users/2")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .body("name", is("morpheus"))
                        .body("job", is("zion resident")));
    }

    @Test
    void registerSuccessful() {
        RegisterBodyModel registerData = new RegisterBodyModel();
        registerData.setEmail("eve.holt@reqres.in");
        registerData.setPassword("pistol");

        RegisterResponseModel response = step("Регистрация пользователя", () ->
                given()
                        .filter(withCustomTemplates())
                        .log().uri()
                        .log().body()
                        .log().headers()
                        .body(registerData)
                        .contentType(JSON)

                        .when()
                        .post("/register")

                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(RegisterResponseModel.class));

        step("Проверка ответа на регистрацию", () -> {
            assertEquals(4, response.getId(), "ID should be 4");
            assertNotNull(response.getToken(), "Token should not be null");
        });
    }
}