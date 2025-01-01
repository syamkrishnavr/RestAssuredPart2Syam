package Part2;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class DifferentPostRequestBody {

    //Post method using HashMap

    @Test(priority = 1)
    void payloadUsingHashMap(){

        HashMap data = new HashMap<>();
        data.put("name","Syam");
        data.put("job","Police");


        given().contentType("application/json").body(data)

                .when().post("https://reqres.in/api/users")

                .then().statusCode(201)
                .body("name",equalTo("Syam"))
                .body("job",equalTo("Police")).log().all();

    }

    @Test(priority = 2)
    void payloadUsingJsonLibrary(){

        JSONObject data = new JSONObject();
        data.put("name","Rahul");
        data.put("job","Driver");

        String jsonString = data.toString();


        given().contentType("application/json").body(jsonString)

                .when().post("https://reqres.in/api/users")

                .then().statusCode(201)
                .body("name",equalTo("Rahul"))
                .body("job",equalTo("Driver")).log().all();

    }

    @Test(priority = 3)
    void payloadUsingPOJO(){

        POJOPayload data = new POJOPayload();
        data.setName("Sanij");
        data.setJob("King");


        given().contentType("application/json").body(data)

                .when().post("https://reqres.in/api/users")

                .then().statusCode(201)
                .body("name",equalTo("Sanij"))
                .body("job",equalTo("King")).log().all();

    }

    @Test(priority = 4)
    void payloadUsingExternalFile() throws FileNotFoundException {

        File f = new File(".\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);



        given().contentType("application/json").body(data.toString())

                .when().post("https://reqres.in/api/users")

                .then().statusCode(201)
                .body("name",equalTo("sumesh"))
                .body("job",equalTo("rider")).log().all();

    }
}
