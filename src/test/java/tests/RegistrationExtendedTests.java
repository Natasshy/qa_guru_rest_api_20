package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.RegistrationBodyLombokModel;
import models.RegistrationResponseLombokModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.RegistrationSpec.registrationRequestSpec;
import static specs.RegistrationSpec.registrationResponseSpec;

public class RegistrationExtendedTests {

    @Test
    void successfulRegistrationWithLombokModelsTest() {
        RegistrationBodyLombokModel registrationData = new RegistrationBodyLombokModel();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("cityslicka");

        RegistrationResponseLombokModel registrationResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(registrationData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(RegistrationResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken());
    }

    @Test
    void successfulRegistrationWithAllureTest() {
        RegistrationBodyLombokModel registrationData = new RegistrationBodyLombokModel();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("cityslicka");

        RegistrationResponseLombokModel registrationResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .filter(new AllureRestAssured())
                .contentType(JSON)
                .body(registrationData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(RegistrationResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken());
    }

    @Test
    void successfulRegistrationWithCustomAllureTest() {
        RegistrationBodyLombokModel registrationData = new RegistrationBodyLombokModel();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("cityslicka");

        RegistrationResponseLombokModel registrationResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .filter(withCustomTemplates())
                .contentType(JSON)
                .body(registrationData)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(RegistrationResponseLombokModel.class);

        assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken());
    }

    @Test
    void successfulRegistrationWithStepsTest() {
        RegistrationBodyLombokModel registrationData = new RegistrationBodyLombokModel();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("cityslicka");

        RegistrationResponseLombokModel registrationResponse = step("Make request", () ->
                given()
                        .log().uri()
                        .log().method()
                        .log().body()
                        .filter(withCustomTemplates())
                        .contentType(JSON)
                        .body(registrationData)
                        .when()
                        .post("https://reqres.in/api/register")
                        .then()
                        .log().status()
                        .log().body()
                        .statusCode(200)
                        .extract().as(RegistrationResponseLombokModel.class));

        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken()));
    }


    @Test
    void successfulRegistrationWithSpecsTest() {
        RegistrationBodyLombokModel registrationData = new RegistrationBodyLombokModel();
        registrationData.setEmail("eve.holt@reqres.in");
        registrationData.setPassword("cityslicka");

        RegistrationResponseLombokModel registrationResponse = step("Make request", () ->
                given(registrationRequestSpec)
                        .body(registrationData)
                        .when()
                        .post("/register")
                        .then()
                        .spec(registrationResponseSpec)
                        .extract().as(RegistrationResponseLombokModel.class));

        step("Check response", () ->
                assertEquals("QpwL5tke4Pnpja7X4", registrationResponse.getToken()));
    }


}
