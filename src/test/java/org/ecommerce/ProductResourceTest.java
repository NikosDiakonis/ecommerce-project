package org.ecommerce;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.ecommerce.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
public class ProductResourceTest {
    @BeforeEach
    @Transactional
    public void cleanDatabase() {
        Product.deleteAll(); // Αδειάζει τον πίνακα Product
    }

    @Test
    @TestTransaction
    public void shouldReturn201() {
        Product product = new Product("testProductOne", 60.0, "testSku1");
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
        Product product = new Product("testProductTwo", 60.0, "testSku2");

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
        Product product = new Product("testProductThree", 60.0, "duplSku");
        Product product2 = new Product("testProdTres", 60.0, "duplSku");
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

    @Test
    @TestTransaction
    public void shouldTestPagination() {
        for(int i = 0; i <= 14; i++) {
            Product product = new Product("testProductNum" + i, 1*i, "testSku" + i);
            given()
            .contentType(ContentType.JSON)
                    .body(product)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);
        }
        given()
        .contentType(ContentType.JSON)
                .when()
                .get("/products?page=0&size=10")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10));

    }

    @Test
    @TestTransaction
    public void shouldTestSortingByName() {
        for(int i = 0; i <= 14; i++) {
            Product product = new Product("testProductNo" + i, 1*i, "testSku" + i);
            given()
                    .contentType(ContentType.JSON)
                    .body(product)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);
        }
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products?page=0&size=10&sortBy=name")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("[0].name", equalTo("testProductNo0"));

    }
    @Test
    @TestTransaction
    public void shouldTestSortingByPrice() {
        for(int i = 0; i <= 14; i++) {
            Product product = new Product("testProductNo" + i, 1*i, "testSku" + i);
            given()
                    .contentType(ContentType.JSON)
                    .body(product)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);
        }
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/products?page=0&size=10&sortBy=price")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10))
                .body("[0].price", equalTo(0.0f));


    }
}
