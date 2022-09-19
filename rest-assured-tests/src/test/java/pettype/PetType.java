package pettype;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import java.util.Date;
import java.util.Map;

public class PetType {
    String baseUrl;
    int petTypeId;
    String petTypeName;

    public void create() {
        System.out.println("@Set up values");
        petTypeName = String.format("New pet type # %f", Math.random());
        baseUrl = "http://localhost:9966/petclinic/api/pettypes/";
        petTypeId = 1;
    }
    public Response callGetPetTypeList() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.get(baseUrl).then().contentType(ContentType.JSON).extract().response();
        return response;
    }
    public int getPetTypeIdByName () {
        Response petTypesList = callGetPetTypeList();
        Object str = petTypesList.jsonPath().getList(".").stream()
                .map(m -> (Map<String, Object>) m)
                .filter(m -> m.get("name").equals(petTypeName))
                .map(m -> m.get("id"))
                .findAny().get();
        System.out.println("Pet type " + petTypeName + " has id " + str);
        return Integer.parseInt(str.toString());

        //       Map<String, String> petTypes = petTypesList.jsonPath().getMap("name");
//        System.out.println(petTypes.get("id"));
    }
    public int callDeletePetType(int petTypeId1) {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        Response response = request.delete(baseUrl+ petTypeId1);
        int statusCode = response.getStatusCode();
        System.out.println("The status code for delete pet type received: " + statusCode);
        return statusCode;
    }
    public int addPetType() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject requestBody = new JSONObject();
        requestBody.put("name", petTypeName);
        requestBody.put("id", petTypeId);
        request.body(requestBody.toJSONString());
        Response response = request.post(baseUrl);
        petTypeId = getPetTypeIdByName();
        return response.getStatusCode();
    }
}
