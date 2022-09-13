package pettype;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;

import static org.hamcrest.Matchers.is;
public class TestRestClass {
    String baseUrl;
    public int petTypeId;
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed: set up base url");
        baseUrl = "http://localhost:9966/petclinic/api/pettypes/";
        petTypeId = 20;
    }
    @Test
    public void getPetTypeName(){
    RestAssured.
            when().get(baseUrl + "1").
            then().assertThat().statusCode(200).
            and().body("name", is("cat"));
    }

    @Test
    public void postPetType() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        String someRandomString = String.format("New pet type # %1$TH%1$TM%1$TS", new Date());
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", someRandomString);
        requestBody.put("id", petTypeId);
        request.body(requestBody.toJSONString());
        Response response = request.post(baseUrl);
        int statusCode = response.getStatusCode();
        System.out.println("The status code received: " + statusCode);
        Assertions.assertEquals(statusCode, 201);
    }
    @Test
    public void deleteNotExistingPetType() {
        int statusCode = callDeletePetType(petTypeId+1);
        Assertions.assertEquals(statusCode, 404);
    }
    @Test
    public void deletePetType() {
        int statusCode = callDeletePetType(petTypeId);
        Assertions.assertEquals(statusCode, 204);
    }

    private int callDeletePetType(int petTypeId1) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.delete(baseUrl+ petTypeId1);
        int statusCode = response.getStatusCode();
        System.out.println("The status code received: " + statusCode);
        return statusCode;
    }
}
