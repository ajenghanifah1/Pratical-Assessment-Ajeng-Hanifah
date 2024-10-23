import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.BeforeSuite;
import test.ApplicationConfig;

import java.util.Properties;

public class BaseTestUser {
    private static Properties environment;
    private ApplicationConfig cfg = ConfigFactory.create(ApplicationConfig.class);


    @BeforeSuite(description = "Configure URI")
    public void SetUpSuite(){
        RestAssured.baseURI = cfg.host();
        RestAssured.basePath = cfg.baseUser();
        RestAssured.defaultParser = Parser.JSON;
        RestAssured.filters(new AllureRestAssured());
}
}


