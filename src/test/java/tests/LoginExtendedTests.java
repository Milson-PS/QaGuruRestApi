package tests;

import models.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static specs.UsersSpec.*;

@Tag("API")
public class LoginExtendedTests extends TestBase {

    @Test
    void loginSuccessfulTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("eve.holt@reqres.in");
        authData.setPassword("cityslicka");

        LoginResponseModel response = step("Отправка запроса на аутентификацию и получение токена", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .when()
                        .post()
                        .then()
                        .spec(loginResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Проверка токена", () ->
                assertNotNull(response.getToken(), "Token should not be null"));
    }

    @Test
    void loginUnsuccessfulTest() {
        LoginBodyModel authData = new LoginBodyModel();
        authData.setEmail("peter@klaven");

        LoginResponseModel response = step("Отправка запроса на аутентификацию с неверными данными", () ->
                given(loginRequestSpec)
                        .body(authData)
                        .when()
                        .post()
                        .then()
                        .spec(loginUnsuccessfulResponseSpec)
                        .extract().as(LoginResponseModel.class));

        step("Проверка сообщения об ошибке", () ->
                assertEquals("Missing password", response.getError(), "Error message should be 'Missing password'"));
    }

    @Test
    void createUsersTest() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("morpheus");
        userData.setJob("leader");

        step("Создание пользователя", () ->
                given(createUserRequestSpec)
                        .body(userData)
                        .when()
                        .post()
                        .then()
                        .spec(createUserResponseSpec)
                        .body("name", is("morpheus"))
                        .body("job", is("leader")));
    }

    @Test
    void updateUsersTest() {
        UserBodyModel userData = new UserBodyModel();
        userData.setName("morpheus");
        userData.setJob("zion resident");

        step("Обновление пользователя", () ->
                given(updateUserRequestSpec)
                        .body(userData)
                        .when()
                        .put()
                        .then()
                        .spec(updateUserResponseSpec)
                        .body("name", is("morpheus"))
                        .body("job", is("zion resident")));
    }

    @Test
    void registerSuccessfulTest() {
        RegisterBodyModel registerData = new RegisterBodyModel();
        registerData.setEmail("eve.holt@reqres.in");
        registerData.setPassword("pistol");

        RegisterResponseModel response = step("Регистрация пользователя", () ->
                given(registerRequestSpec)
                        .body(registerData)
                        .when()
                        .post()
                        .then()
                        .spec(registerResponseSpec)
                        .extract().as(RegisterResponseModel.class));

        step("Проверка ответа на регистрацию", () -> {
            assertEquals(4, response.getId(), "ID should be 4");
            assertNotNull(response.getToken(), "Token should not be null");
        });
    }
}
