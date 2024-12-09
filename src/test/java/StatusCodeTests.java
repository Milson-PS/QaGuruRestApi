import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class StatusCodeTests {


@Test
void checkTotal12() {
    get("https://reqres.in/api/unknown")
            .then()
            .body("total", is(12));
}


    @Test
    void checkNoTotal12() {
        get("https://reqres.in/api/unknown")
                .then()
                .body("total", is(13));
    }



@Test
void checkTotalWithLogs() {
    given()
            .log().all()
            .get("https://reqres.in/api/users?page=2")
            .then()
            .log().all()
            .body("page", is(2));
}



@Test
void checkSingleUserNotFound() {
    given()
            .log().uri()
            .get("https://reqres.in/api/users/23")
            .then()
            .log().status()
            .log().body()
            .statusCode(404)
            .body(equalTo("{}"));

}
}

