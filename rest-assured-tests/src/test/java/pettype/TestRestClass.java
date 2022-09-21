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
import java.util.Optional;

import static org.hamcrest.Matchers.is;
public class TestRestClass {
    PetType pt;
    @BeforeAll
    static void setup() {
        System.out.println("@BeforeAll executed");

    }

    @BeforeEach
    void setupThis() {
        System.out.println("@BeforeEach executed: use new pet type");
        pt = new PetType();
        pt.create();
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getPetType() {
        pt.checkPetTypeName(1, "cat");
        pt.addPetType();
        pt.checkPetTypeName(pt.petTypeId, pt.petTypeName);
    }
    @Test
    public void postPetType() {
        int statusCode = pt.addPetType();
        System.out.println("The status code for post pet type received: " + statusCode);
        Assertions.assertEquals(statusCode, 201);
    }
    @Test
    public void postExistingPetType() {
        int statusCode = pt.addPetType();
        pt.petTypeName = String.format("New pet type # %f", Math.random());
        statusCode = pt.addPetType();
        System.out.println("The status code for post pet type twice received: " + statusCode);
        Assertions.assertEquals(statusCode, 201);
    }

    @Test
    public void putPetType() {
        pt.addPetType();
        pt.petTypeName = String.format("New pet type # %f", Math.random());
        int statusCode = pt.updatePetType();
        System.out.println("The status code for put pet type received: " + statusCode);
        Assertions.assertEquals(statusCode, 204);
    }
    @Test
    public void deletePetType() {
        pt.addPetType();
        int statusCode = pt.callDeletePetType(pt.petTypeId);
        Assertions.assertEquals(statusCode, 204);
    }
    @Test
    public void deleteNotExistingPetType() {
        pt.addPetType();
        int statusCode = pt.callDeletePetType(pt.petTypeId+1);
        Assertions.assertEquals(statusCode, 404);
    }
}
