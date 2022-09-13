package pettype;

import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Date;
import java.util.Map;

import static org.hamcrest.Matchers.is;
public class TestRestClass {
    String baseUrl;
    int petTypeId;
    String petName;
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");
    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed: set up base url");
        baseUrl = "http://localhost:9966/petclinic/api/pettypes/";
        petName = String.format("New pet type # %1$TH%1$TM%1$TS", new Date());
        petTypeId = 20;
        RestAssured.defaultParser = Parser.JSON;
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

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", petName);
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
    public Response callGetPetTypeList() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.get(baseUrl).then().contentType(ContentType.JSON).extract().response();
        return response;
    }

    public int getPetTypeIdByName () {
        Response  petTypesList = callGetPetTypeList();
        Map<String, String> petTypes = petTypesList.jsonPath().getMap("name[0]");
        System.out.println(petTypes.get("id"));
        return 1;
    }
}
