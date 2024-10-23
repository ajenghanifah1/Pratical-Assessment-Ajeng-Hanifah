import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Feature("Test Mealplan")
public class mealPlanTest extends BaseTestSpooner {
    String apiKey = "a33e99de685641c9b8ffc76ce710f3db";
    String username = "selvi317";
    String hash = "ed14ef99ff1b5d1991528a92fd0b8e1c5d0111e0";

    @Test(description = "Test Meal Planner Generate")
    public void testMealPlannerGenerate(){
        given().queryParam("apiKey", apiKey)
                .log().ifValidationFails()
                .when()
                .get("mealplanner/generate")
                .then()
                .statusCode(200)
                .body("week.monday.meals.size()", equalTo(3))
                .extract().response();
    }

    @Test(description = "Test Meal Planner Generate With Parameter")
    public void testMealPlannerParameterGenerate(){
        given().queryParams("apiKey",apiKey)
                .queryParams("targetCalories", 1000)
                .queryParams("diet", "vegetarian")
                .log().ifValidationFails()
                .when()
                .get("mealplanner/generate")
                .then()
                .statusCode(200)
                .extract().response();

    }

    @Test(description = "Add Mealplan")
    public void testAddMealPlan(){
        String requestBody="{\n" +
                "    \"date\": 1589500800,\n" +
                "    \"slot\": 1,\n" +
                "    \"position\": 0,\n" +
                "    \"type\": \"INGREDIENTS\",\n" +
                "    \"value\": {\n" +
                "        \"ingredients\": [\n" +
                "            {\n" +
                "                \"name\": \"1 banana\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .queryParam("apiKey", apiKey)
                .queryParam("hash", hash)
                .body(requestBody)
                .when()
                .post("mealplanner/{username}/items", username)
                .then()
                .statusCode(200)
                .extract().response();
    }

    @Test(description = "Test Image Classification")
    public void testImageClassification(){
        given().queryParam("apiKey", apiKey)
                .queryParam("imageUrl","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRBzs64ItCPD0EGMSEXaW_g--li4KCc9Ur6kQ&s")
                .log().ifValidationFails()
                .when()
                .get("food/images/classify")
                .then()
                .statusCode(200)
                .extract().response();
    }
}
