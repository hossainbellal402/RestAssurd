import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ApiTest {

    @Test
    public void ApiTest() throws JsonProcessingException {

        RestAssured.baseURI="http://qa.taltektc.com/api/";
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);
        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);

        Response rest = RestAssured.given()
                .contentType("application/json")
                .body("{\n" +
                        "    \"firstName\" : \"Jhon\",\n" +
                        "    \"lastName\" : \"Doe\",\n" +
                        "    \"email\"     : \"jhon.doa22@gmail.com\",\n" +
                        "    \"password\"  : \"1234\",\n" +
                        "    \"confirmPassword\"  : \"1234\",\n" +
                        "    \"dob\"       : {\n" +
                        "        \"year\"      : 2013,\n" +
                        "        \"month\"     : 12,\n" +
                        "        \"day\"       : 32\n" +
                        "    },\n" +
                        "    \"gender\"    : \"male\",\n" +
                        "    \"agree\"     : true\n" +
                        "}")
                .post("signup");

        System.out.println(rest.getStatusCode());
        //System.out.println(rest.getBody().asString());
        Assert.assertEquals(rest.getStatusCode(),400);
        ObjectMapper map= new ObjectMapper();
        JsonNode js = map.readTree(rest.getBody().asString());
        System.out.println(js.get("success"));
        System.out.println(js.get("id"));


    }
}






