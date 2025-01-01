package Part4;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class ParsingJsonResponseData {
    boolean status = false;
    int total_id = 0;
    @Test
    void responseValidation1(){
        given()
                .when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200)
                .body("data[3].id",equalTo(10));
    }

    @Test
    void responseValidation2(){ //multiple assertions can be performed
       Response res =  given()
                .when().get("https://reqres.in/api/users?page=2");

        Assert.assertEquals(res.getStatusCode(),200);
        Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");

        String email = res.jsonPath().get("data[4].email").toString();
        Assert.assertEquals(email,"george.edwards@reqres.in");
    }

    @Test
    void responseValidationJSONObject(){ //multiple assertions can be performed
        Response res =  given().contentType(ContentType.JSON)
                .when().get("https://reqres.in/api/users?page=2");


        JSONObject jsonObject = new JSONObject(res.asString());

        //search for first_name
        for (int i = 0; i <jsonObject.getJSONArray("data").length() ; i++) {

            String first_name = jsonObject.getJSONArray("data").getJSONObject(i).get("first_name").toString();
            System.out.println(first_name);

            if (first_name.equals("Tobias")){
                status = true;
                break;
            }
        }
        Assert.assertEquals(status,true);

        //taking sum ids in objects
        for (int i = 0; i <jsonObject.getJSONArray("data").length() ; i++) {

            String ids = jsonObject.getJSONArray("data").getJSONObject(i).get("id").toString();
            total_id = total_id + Integer.parseInt(ids);
        }
        System.out.println(total_id);
        Assert.assertEquals(total_id,57);
    }
}
