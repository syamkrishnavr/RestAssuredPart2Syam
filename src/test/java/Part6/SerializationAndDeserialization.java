
package Part6;

import Part2.POJOPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class SerializationAndDeserialization {
        @Test
    void POJO2JsonTest() throws JsonProcessingException {
            POJOPayload dataP2J = new POJOPayload();
            dataP2J.setJob("Hardworker");
            dataP2J.setName("John");

            ObjectMapper objectMapper = new ObjectMapper();
            String js = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(dataP2J);
            System.out.println(js);
        }

        @Test
        void Json2POJOTest() throws JsonProcessingException {
            String jsonData = "{\n" +
                    "  \"name\" : \"John\",\n" +
                    "  \"job\" : \"Hardworker\"\n" +
                    "}";

            ObjectMapper obj = new ObjectMapper();
            POJOPayload pj1 = obj.readValue(jsonData,POJOPayload.class);
            System.out.println(pj1.getJob());
            System.out.println(pj1.getName());
        }
}
