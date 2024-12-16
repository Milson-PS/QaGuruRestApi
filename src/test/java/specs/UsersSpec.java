package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.internal.http.Status;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static io.restassured.http.ContentType.JSON;

public class UsersSpec {
    public static RequestSpecification LoginRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/login");

    public static ResponseSpecification LoginResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static ResponseSpecification LoginUnsuccessfulResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(400)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification CreateUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/users");

    public static ResponseSpecification CreateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification UpdateUserRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/users/2");

    public static ResponseSpecification UpdateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();

    public static RequestSpecification RegisterRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().body()
            .log().headers()
            .contentType(JSON)
            .baseUri("https://reqres.in")
            .basePath("/api/register");

    public static ResponseSpecification RegisterResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(STATUS)
            .log(BODY)
            .build();
}