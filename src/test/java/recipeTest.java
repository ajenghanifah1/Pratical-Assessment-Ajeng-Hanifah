import io.qameta.allure.Feature;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Feature("Recipe")
public class recipeTest extends BaseTestSpooner {
    String apiKey = "8b585c39fc5844928293f8a233d0b803";
    int recipeId = 222;

    @Test(description = "Search Recipes")
    public void testSearchRecipes(){
        given().contentType(ContentType.JSON)
                .queryParam("apiKey", apiKey) // Replace with your actual API key
                .queryParam("query", "pasta") // Example search term (optional)
                .queryParam("number", 2) // Limit the number of results (optional)
                .when()
                .get("/recipes/complexSearch")
                .then()
                .statusCode(200) // Validate the response status code is 200 (OK)
                .extract().response();
    }
    @Test(description = "Search Recipes By Nutrients")
    public void testFindByNutrients(){
        given().contentType(ContentType.JSON)
                .queryParam("apiKey", apiKey) // Replace with your actual API key
                .queryParam("minCalories", 200) // Minimum calories per recipe
                .queryParam("maxCalories", 500) // Maximum calories per recipe
                .queryParam("minProtein", 10) // Minimum protein per recipe
                .queryParam("maxFat", 20) // Maximum fat per recipe
                .queryParam("number", 5) // Limit number of results to 5
                .when()
                .get("/recipes/findByNutrients")
                .then()
                .statusCode(200) // Validate the response status code is 200 (OK)
                .extract().response();
    }
    @Test(description = "Search Recipes By Ingredients")
    public void testFindByIngredients(){
        given().contentType(ContentType.JSON)
            .queryParam("apiKey", apiKey) // Replace with your actual API key
            .queryParam("ingredients", "tomato,cheese,garlic") // Ingredients to search for
            .queryParam("number", 5) // Limit the number of results to 5
            .queryParam("ranking", 1) // Rank recipes by the number of used ingredients
            .when()
            .get("/recipes/findByIngredients")
            .then()
            .statusCode(200) // Validate the response status code is 200 (OK)
            .extract().response();
    }
    @Test(description = "Taste By ID")
    public void testTasteById(){
        given().contentType(ContentType.JSON)
                .queryParam("apiKey", apiKey) // Replace with your actual API key
                .when()
                .get("/recipes/" + recipeId + "/tasteWidget.json") // Replace {id} with recipeId
                .then()
                .statusCode(200) // Validate the response status code is 200 (OK)
                .extract().response();
    }
    @Test(description = "Price Breakdown By ID")
    public void testPriceBreakdownById(){
        given().contentType(ContentType.JSON)
                .queryParam("apiKey", apiKey) // Replace with your actual API key
                .when()
                .get("/recipes/" + recipeId + "/priceBreakdownWidget.json") // Replace {id} with recipeId
                .then()
                .statusCode(200) // Validate the response status code is 200 (OK)
                .extract().response();
    }
}
