import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Feature("User Module")
public class userRequestTest extends BaseTestUser{

    @Test(priority = 1)
    public void addUserTest(){
        String requestBody = "\"id\": 317,\n" +
                "                \"username\": \"selvi317\",\n" +
                "                \"firstName\": \"Selvi\",\n" +
                "                \"lastName\": \"Yulianita\",\n" +
                "                \"email\": \"selvi@gmail.com\",\n" +
                "                \"password\": \"User123\",\n" +
                "                \"phone\": \"0820119646766\",\n" +
                "                \"userStatus\": 0";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test(priority = 2)
    public void findByUsernameTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/selvi123")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 3)
    public void loginTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("username", "selvi317")
                .queryParam("password", "User123")
                .when()
                .get("/login")
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(priority = 4)
    public void updateUserTest(){
        String requestBody = "\"id\": 317,\n" +
                "                \"username\": \"selvi3174\",\n" +
                "                \"firstName\": \"Selvi\",\n" +
                "                \"lastName\": \"Yulianita\",\n" +
                "                \"email\": \"selvi@gmail.com\",\n" +
                "                \"password\": \"User123\",\n" +
                "                \"phone\": \"0820119646766\",\n" +
                "                \"userStatus\": 0";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .log().ifValidationFails()
                .when()
                .post()
                .then()
                .statusCode(200);
    }

    @Test(priority = 5)
    public void logoutTest(){
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .get("/logout")
                .then()
                .statusCode(200)
                .body("message", equalTo("Logged out successfully"))
                .extract().response();
    }

    @Test(priority = 6)
    public void deleteUserTest(){
        given()
                .when()
                .delete("/selvi3174")
                .then()
                .statusCode(200)
                .body("message", equalTo("User deleted successfully"))
                .extract().response();
    }

}
