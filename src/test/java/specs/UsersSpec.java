package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class UsersSpec {
    public static RequestSpecification loginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON)
            .basePath("/api/login");

    public static ResponseSpecification loginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification loginUnsuccessfulResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification createUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON)
            .basePath("/api/users");

    public static ResponseSpecification createUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification updateUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON)
            .basePath("/api/users/2");

    public static ResponseSpecification updateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification registerRequestSpec = with()
            .filter(withCustomTemplates())
            .log().all()
            .contentType(JSON)
            .basePath("/api/register");

    public static ResponseSpecification registerResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}
