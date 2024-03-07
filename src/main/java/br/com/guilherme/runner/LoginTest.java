package br.com.guilherme.runner;

import br.com.guilherme.core.RestAssuredConfig;
import br.com.guilherme.request.Login;
import br.com.guilherme.util.YAMLConfigReader;
import org.junit.Before;
import org.junit.Test;

public class LoginTest {

    private final Login login = new Login();

    @Before
    public void configTest() {
        RestAssuredConfig.setBaseURI(YAMLConfigReader.getValueByKey("base_url"));
    }

    @Test
    public void StartTest() {
        login.registerUser();
        login.registerUserUnsuccessful();
        login.loginUser();
        login.loginUserUnsuccessful();
    }
}
