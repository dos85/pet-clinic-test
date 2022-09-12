package pettype;

import org.junit.Test;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.is;
public class TestRestClass {
    @Test
    public void postmanFirstGetTest(){
    RestAssured.
            when().get("http://localhost:9966/petclinic/api/pettypes/1").
            then().assertThat().statusCode(200).
            and().body("name", is("cat"));
    }
}
