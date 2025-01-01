package Part7;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Authentications {// api details in postman
    @Test
    void testBasic(){
        given().auth().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200)
                .body("authenticated",equalTo(true)).log().all();
    }

    @Test
    void testDigest(){
        given().auth().digest("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200)
                .body("authenticated",equalTo(true)).log().all();
    }

    @Test
    void testPreemptive(){
        given().auth().preemptive().basic("postman","password")
                .when().get("https://postman-echo.com/basic-auth")
                .then().statusCode(200)
                .body("authenticated",equalTo(true)).log().all();
    }

    @Test
    void testBearerToken(){
        String bearerToken = "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";

        given().headers("Authorization","Bearer " + bearerToken)
                .when().get("https://api.github.com/user/repos")
                .then().statusCode(200).log().all();
    }
}
