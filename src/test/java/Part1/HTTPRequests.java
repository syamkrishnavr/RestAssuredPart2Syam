package Part1;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;

import java.util.HashMap;

public class HTTPRequests {

    int id;

    @Test
    void getUser(){
        given().when().get("https://reqres.in/api/users?page=2")
                .then().statusCode(200).body("page",equalTo(2)).log().all();
    }


    @Test(priority = 1)
    void createUser(){
        HashMap data = new HashMap<>();
        data.put("name","syam");
        data.put("job","........");

        id=given().contentType("application/json").body(data)
                .when().post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
                //.then().statusCode(201).body("name",equalTo("syam"));
    }

    @Test(priority = 2,dependsOnMethods = "createUser")
    void updateUser(){
        HashMap data = new HashMap<>();
        data.put("name","syam");
        data.put("job","police");

        given().contentType("application/json").body(data)
                .when().put("https://reqres.in/api/users/"+id)
                .then().statusCode(200).body("job",equalTo("police")).log().all();
    }

    @Test(priority = 3)
    void deleteUser(){
        given()
                .when().delete("https://reqres.in/api/users/"+id)
                .then().statusCode(204).log().all();
    }

}
