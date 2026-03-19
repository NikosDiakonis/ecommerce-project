package org.ecommerce;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ProductResourceTest {
    @Test
    public void shouldReturn201() {
        Product product = new Product("testProduct", 60.0, "testSku");
        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                        .statusCode(201);
    }
}
