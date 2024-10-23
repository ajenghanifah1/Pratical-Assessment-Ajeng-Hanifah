import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class ConnectUserTest extends BaseTestSpooner {
    @Test(description = "Get Username and Hash")
    public void testConnectUser(){
        String apiKey = "a33e99de685641c9b8ffc76ce710f3db";
        String requestBody = "{\n" +
                "    \"username\": \"selvi317\",\n" +
                "    \"firstName\": \"Selvi\",\n" +
                "    \"lastName\": \"Yulianita\",\n" +
                "    \"email\": \"selvi.mutia@gmail.com\"\n" +
                "}";

        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .body(requestBody)
                .post("users/connect")
                .then()
                .statusCode(200)
                .extract().response();
    }
}

