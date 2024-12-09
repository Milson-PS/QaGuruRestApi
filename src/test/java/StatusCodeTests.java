import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class StatusCodeTests {

    @BeforeAll
    public static void setupRestAssured(){
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }


@Test
void checkTotal12() {
    get("/unknown")
            .then()
            .body("total", is(12));
}


    @Test
    void checkNoTotal12() {
        get("/unknown")
                .then()
                .body("total", is(13));
    }



    @Test
    void checkTotalWithLogs() {
        given()
                .log().all()
                .queryParam("page", "2")
                .get("/users")
                .then()
                .log().all()
                .body("page", is(2));
    }



@Test
void checkSingleUserNotFound() {
    given()
            .log().uri()
            .get("/users/23")
            .then()
            .log().status()
            .log().body()
            .statusCode(404)
            .body(equalTo("{}"));

}
}

