package com.codefeeding;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegistrationApiTest {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test
    void testRegisterUser() {
        String jsonBody = "{ \"name\": \"vkk User\", \"email\": \"Devopstest@gmail.com\", \"password\": \"984999\" }";

        given()
            .contentType(ContentType.JSON)
            .body(jsonBody)
        .when()
            .post("/api/registration")
        .then()
            .statusCode(200)
            .body("id", notNullValue());
    }
}
