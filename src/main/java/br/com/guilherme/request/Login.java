package br.com.guilherme.request;

import br.com.guilherme.core.BaseRequest;
import br.com.guilherme.model.LoginModel;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class Login extends BaseRequest {

    private final LoginModel loginModel = new LoginModel();


    public void registerUser() {
        makeBody();
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/register")
                .then().log().all().extract().response();
    }

    public void loginUser() {
        makeBody();
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/login")
                .then().log().all().extract().response();
    }

    public void registerUserUnsuccessful() {
        makeBodyUnsuccessful();
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/register")
                .then().log().all().extract().response();
    }

    public void loginUserUnsuccessful() {
        makeBodyUnsuccessful();
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/login")
                .then().log().all().extract().response();
    }

    private void makeBody() {
        loginModel.setEmail("eve.holt@reqres.in");
        loginModel.setPassword("pistol");
    }

    private void makeBodyUnsuccessful() {
        loginModel.setEmail("eve.holt@reqres.in");
        loginModel.setPassword(null);
    }
}
