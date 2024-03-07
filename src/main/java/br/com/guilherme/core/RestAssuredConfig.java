package br.com.guilherme.core;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class RestAssuredConfig {

    public static void setBaseURI(String BaseURI) {
        RestAssured.baseURI = BaseURI;
        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
