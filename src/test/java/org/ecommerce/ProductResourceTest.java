package org.ecommerce;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.ecommerce.domain.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class ProductResourceTest {
    @Test
    @TestTransaction
    public void shouldReturn201() {
        Product product = new Product("testProduct1", 60.0, "testSku1");
        given()
                .contentType(ContentType.JSON)
                .body(product)
                .when()
                .post("/products")
                .then()
                        .statusCode(201);
    }

    @Test
    @TestTransaction
    public void shouldReturn200() {
        given()
                .when()
                .get("/products")
                .then()
                    .statusCode(200);
    }
    @Test
    @TestTransaction
    public void shouldReturnSavedProduct() {
        Product product = new Product("testProduct2", 60.0, "testSku2");

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
                .body("sku", hasItem("testSku2"));
    }

    @Test
    @TestTransaction
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
    @TestTransaction
    public void shouldReturn400WhenDuplicateSku(){
        Product product = new Product("testProduct3", 60.0, "duplSku");
        Product product2 = new Product("testProd3", 60.0, "duplSku");
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
    @TestTransaction
    public void shouldReturn400WhenDuplicateName(){
        Product product = new Product("dublName", 60.0, "testSku4");
        Product productTwo = new Product("dublName", 60.0, "testerSku4");

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
