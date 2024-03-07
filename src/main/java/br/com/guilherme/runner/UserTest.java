package br.com.guilherme.runner;

import br.com.guilherme.core.RestAssuredConfig;
import br.com.guilherme.request.User;
import br.com.guilherme.util.YAMLConfigReader;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    private final User user = new User();

    @Before
    public void configTest() {
        RestAssuredConfig.setBaseURI(YAMLConfigReader.getValueByKey("base_url"));
    }

    @Test
    public void StartTest() {
        user.getAllUsers();
        user.getSingleUser();
        user.getUserNotFound();
        user.getAllResources();
        user.getSingleResource();
        user.getResourceNotFound();
        user.createUser();
        user.updateUserWithPut();
        user.updateUserWithPatch();
        user.deleteUser();
    }

}
