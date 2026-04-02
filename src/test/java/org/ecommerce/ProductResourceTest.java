package org.ecommerce;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.hasItem;

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

    @Test
    public void shouldReturn200() {
        given()
                .when()
                .get("/products")
                .then()
                    .statusCode(200);
    }
    @Test
    public void shouldReturnSavedProduct() {
        Product product = new Product("testProduct", 60.0, "testSku");

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201);

        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("sku", hasItem("testSku"));
    }

    @Test
    public void shouldReturn400WhenEmptyField(){
        Product product = new Product("", 60.0, "");

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(400);


    }

    @Test
    public void shouldReturn400WhenDuplicateSku(){
        Product product = new Product("testProduct", 60.0, "duplSku");
        Product product2 = new Product("testProd", 60.0, "duplSku");
        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201);

                given()
                        .contentType(ContentType.JSON)
                        .body(product2)
                        .when()
                        .post("/products")
                        .then()
                        .statusCode(400);

    }
    @Test
    public void shouldReturn400WhenDuplicateName(){
        Product product = new Product("dublName", 60.0, "testSku");
        Product productTwo = new Product("dublName", 60.0, "testerSku");

        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                .statusCode(201);
        given()
        .contentType(ContentType.JSON)
                .body(productTwo)
                .when()
                .post("/products")
                .then()
                .statusCode(400);
    }
}
