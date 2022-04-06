package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.entity.Home;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response.Status;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {
    private static final Logger logger = Logger.getLogger(GreetingResourceTest.class);

    @Test
    public String testAddHome() {
        return given()
                .contentType(ContentType.JSON)
                .body(new Home(1999, "str. Dacia"))
                .when().post("/home")
                .then()
                .statusCode(Status.CREATED.getStatusCode())
                .extract().header("location");
    }


    @Test
    public void testGetHome() {
        String[] splits = testAddHome().split("/");
        String location = splits[splits.length - 1];

        logger.info("home/" + location);

        given()
                .when().get("home/" + location)
                .then()
                .statusCode(Status.OK.getStatusCode())
                .body("id", is(1))
                .body("year", is(1999))
                .body("address", is("str. Dacia"));
    }


}