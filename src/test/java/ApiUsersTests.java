import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiUsersTests {

    @Test
    void createUsers() {
        String authData = "{\"name\": \"morpheus\", \"job\": \"leader\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }

    @Test
    void updateUsers() {
        String authData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"));
    }

    @Test
    void registerSuccessful() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";
        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }


    @Test
    void loginSuccessful() {
        String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));

    }
    @Test
    void loginUnSuccessful() {
        String authData = "{\"email\": \"peter@klaven\"}";

        given()
                .body(authData)
                .contentType(JSON)
                .log().uri()
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));

    }


}






