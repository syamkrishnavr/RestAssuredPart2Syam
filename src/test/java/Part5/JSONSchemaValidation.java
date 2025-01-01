package Part5;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class JSONSchemaValidation {
    @Test
    void jsonSchemaValidationTest(){
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("getDataSchema.json"));
    }
}
