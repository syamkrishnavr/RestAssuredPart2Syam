package Part8;
import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetUser {
    @Test
    void testGetUser(ITestContext context){

        int id = (Integer) context.getAttribute("user_id");
        String bearerToken = "7366473bfd8220018d1c32f7bb9edbbb076c3c85dd8248c9894deb410826b889";
        given().headers("Authorization","Bearer " + bearerToken)
                .pathParam("id",id)
                .when().post("https://gorest.co.in/public/v2/users/{id}")
                .then().statusCode(200).log().all();
    }
}
