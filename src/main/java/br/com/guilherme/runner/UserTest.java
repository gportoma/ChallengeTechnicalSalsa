package br.com.guilherme.runner;

import br.com.guilherme.core.RestAssuredConfig;
import br.com.guilherme.model.UserModel;
import br.com.guilherme.util.YAMLConfigReader;
import io.restassured.http.ContentType;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserTest {

    @Before
    public void setUp() {
        RestAssuredConfig.setBaseURI(YAMLConfigReader.getValueByKey("base_url"));
    }

    @Test
    public void getAllUsersTest() {
        given().log().all()
                .queryParam("page", 1)
                .queryParam("per_page", 5)
                .when()
                .get("/users")
                .then().log().all()
                .contentType(containsString("application/json"))
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("total", notNullValue())
                .body("total_pages", notNullValue())
                .body("data", notNullValue())
                .body("support", notNullValue())
                .body("data.email", everyItem(matchesPattern("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")));
    }

    @Test
    public void getSingleUserTest() {
        given().log().all()
                .pathParam("idUser", 1)
                .when()
                .get("/users/{idUser}")
                .then().log().all()
                .statusCode(200)
                .body("data", notNullValue())
                .body("data.id", notNullValue())
                .body("data.email", notNullValue())
                .body("data.first_name", notNullValue())
                .body("data.last_name", notNullValue())
                .body("data.avatar", notNullValue())
                .body("data.email", matchesPattern("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"))
                .body("support", notNullValue())
                .body("support", instanceOf(Object.class))
                .body("support", hasKey("url"))
                .body("support", hasKey("text"));
    }

    @Test
    public void getUserNotFoundTest() {
        given().log().all()
                .pathParam("idUser", 23)
                .when()
                .get("/users/{idUser}")
                .then().log().all()
                .statusCode(404)
                .contentType(containsString("application/json"))
                .body(is("{}"));
    }

    @Test
    public void getAllResourcesTest() {
        given().log().all()
                .when()
                .get("/unknown")
                .then().log().all()
                .statusCode(200)
                .body("page", notNullValue())
                .body("per_page", notNullValue())
                .body("total", notNullValue())
                .body("total_pages", notNullValue())
                .body("data", notNullValue())
                .body("support", notNullValue())
                .body("data", is(not(empty())))
                .body("support", instanceOf(Object.class));
    }

    @Test
    public void getSingleResourceTest() {
        given().log().all()
                .pathParam("idResource", 1)
                .when()
                .get("/unknown/{idResource}")
                .then().log().all()
                .statusCode(200)
                .contentType(containsString("application/json"))
                .body("data", notNullValue())
                .body("data", instanceOf(Object.class))
                .body("support", notNullValue())
                .body("support", instanceOf(Object.class));
    }

    @Test
    public void createUserTest() {
        UserModel userModel = generateRandomUser();
        given().log().all()
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .post("/users")
                .then().log().all()
                .statusCode(201)
                .body("", hasKey("name"))
                .body("", hasKey("job"))
                .body("", hasKey("id"))
                .body("", hasKey("createdAt"));
    }

    @Test
    public void updateUserWithPutTest() {
        UserModel userModel = generateRandomUser();
        given().log().all()
                .pathParam("idUser", 5)
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .put("/users/{idUser}")
                .then().log().all()
                .statusCode(200)
                .body("", hasKey("name"))
                .body("", hasKey("job"))
                .body("", hasKey("updatedAt"));
    }

    @Test
    public void updateUserWithPatchTest() {
        UserModel userModel = generateRandomUser();
        given().log().all()
                .pathParam("idUser", 4)
                .contentType(ContentType.JSON)
                .body(userModel)
                .when()
                .patch("/users/{idUser}")
                .then().log().all()
                .statusCode(200)
                .body("", hasKey("name"))
                .body("", hasKey("job"))
                .body("", hasKey("updatedAt"));
    }

    @Test
    public void deleteUserTest() {
        given().log().all()
                .pathParam("idUserCreated", 40)
                .when()
                .delete("/users/{idUserCreated}")
                .then().log().all()
                .statusCode(204)
                .body(is(emptyString()));
    }

    private UserModel generateRandomUser() {
        UserModel userModel = new UserModel();
        userModel.setName(generateRandomString());
        userModel.setJob("Quality Assurance");
        return userModel;
    }

    private String generateRandomString() {
        int leftLimit = 97;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
