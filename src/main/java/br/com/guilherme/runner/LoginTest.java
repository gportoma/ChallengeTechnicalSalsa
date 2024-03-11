package br.com.guilherme.runner;

import br.com.guilherme.core.RestAssuredConfig;
import br.com.guilherme.model.LoginModel;
import br.com.guilherme.util.YAMLConfigReader;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LoginTest {

    private final LoginModel loginModel = new LoginModel();

    @Before
    public void configTest() {
        RestAssuredConfig.setBaseURI(YAMLConfigReader.getValueByKey("base_url"));
    }

    @Test
    public void registerUser() {
        makeBody("eve.holt@reqres.in", "pistol");
        given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/register")
                .then().log().all()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue());
    }

    @Test
    public void loginUser() {
        makeBody("eve.holt@reqres.in", "pistol");
        given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/login")
                .then().log().all()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasKey("token"));
    }

    @Test
    public void registerUserUnsuccessful() {
        makeBody("eve.holt@reqres.in", null);
        given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/register")
                .then().log().all()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("$", hasKey("error"))
                .body("error", equalTo("Missing password"));
    }

    @Test
    public void loginUserUnsuccessful() {
        makeBody("eve.holt@reqres.in", null);
        given().log().all()
                .contentType(ContentType.JSON)
                .body(loginModel)
                .when()
                .post("/login")
                .then().log().all()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .body("$", hasKey("error"))
                .body("error", equalTo("Missing password"));
    }

    private void makeBody(String email, String password) {
        loginModel.setEmail(email);
        loginModel.setPassword(password);
    }
}
