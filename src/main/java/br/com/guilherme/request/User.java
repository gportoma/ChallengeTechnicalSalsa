package br.com.guilherme.request;

import br.com.guilherme.core.BaseRequest;
import br.com.guilherme.model.UserModel;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class User extends BaseRequest {

    private final UserModel userModel = new UserModel();

    public void getAllUsers() {
        response = given().log().all()
                .queryParam("page", 1)
                .queryParam("per_page", 5)
                .when()
                .get("/users")
                .then().log().all().extract().response();
    }

    public void getSingleUser() {
        response = given().log().all()
                .pathParam("idUser", 1)
                .when()
                .get("/users/{idUser}")
                .then().log().all().extract().response();
    }

    public void getUserNotFound() {
        response = given().log().all()
                .pathParam("idUser", 23)
                .when()
                .get("/users/{idUser}")
                .then().log().all().extract().response();
    }

    public void getAllResources() {
        response = given().log().all()
                .when()
                .get("/unknown")
                .then().log().all().extract().response();
    }

    public void getSingleResource() {
        response = given().log().all()
                .pathParam("idResource", 1)
                .when()
                .get("/unknow/{idResource}")
                .then().log().all().extract().response();
    }

    public void getResourceNotFound() {
        response = given().log().all()
                .pathParam("idResource", 23)
                .when()
                .get("/unknow/{idResource}")
                .then().log().all().extract().response();
    }

    public void createUser() {
        makeBodyCreateUser();
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .post("/users")
                .then().log().all().extract().response();
    }

    private void makeBodyCreateUser() {
        userModel.setName("Teste");
        userModel.setJob("Quality Assurance");
    }

    public void updateUserWithPut() {
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .put("/users")
                .then().log().all().extract().response();
    }

    public void updateUserWithPatch() {
        response = given().log().all()
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .patch("/users")
                .then().log().all().extract().response();
    }

    public void deleteUser() {
        response = given().log().all()
                .pathParam("idUserCreated",40)
                .when()
                .delete("/users/{idUserCreated}")
                .then().log().all().extract().response();
    }

}
