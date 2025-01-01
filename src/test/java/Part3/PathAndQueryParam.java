package Part3;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class PathAndQueryParam {

    @Test
    void testQueryAndPathParam(){
        given()
                .pathParam("mypath","users")
                .queryParam("page",2)
                .queryParam("id",7)

                .when().get("https://reqres.in/api/{mypath}")

                .then().statusCode(200).log().all();
    }

    @Test
    void testCookies(){
        Response res = given()
                .pathParam("mypath","users")
                .queryParam("page",2)
                .queryParam("id",7)

                .when().get("https://reqres.in/api/{mypath}");

        Map<String,String> cookie_value = res.getCookies();
        System.out.println(cookie_value.keySet());

        for (String k:cookie_value.keySet()){
            String cookie_val = res.getCookie(k);
            System.out.println(cookie_val);
        }
    }
}
