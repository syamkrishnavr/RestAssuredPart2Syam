package Part8;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class CreateUser {
    @Test
    void testCreateUser(ITestContext context){
        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");

        String bearerToken = "7366473bfd8220018d1c32f7bb9edbbb076c3c85dd8248c9894deb410826b889";
        int id = given().headers("Authorization","Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .when().post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");

        System.out.println(id);
        context.setAttribute("user_id",id);
    }
}
