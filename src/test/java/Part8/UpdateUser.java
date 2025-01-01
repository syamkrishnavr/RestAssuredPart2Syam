package Part8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UpdateUser {
    @Test
    void testUpdateUser(ITestContext context){

        Faker faker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name",faker.name().fullName());
        data.put("gender","female");
        data.put("email",faker.internet().emailAddress());
        data.put("status","active");

        String bearerToken = "7366473bfd8220018d1c32f7bb9edbbb076c3c85dd8248c9894deb410826b889";
        int id = (int) context.getAttribute("user_id");
        given().headers("Authorization","Bearer " + bearerToken)
                .contentType("application/json")
                .body(data.toString())
                .pathParam("id",id)
                .when().post("https://gorest.co.in/public/v2/users/{id}")
                        .then().statusCode(200).log().all();
    }
}
