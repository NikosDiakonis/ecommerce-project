package org.ecommerce;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import org.ecommerce.domain.DigitalProduct;
import org.ecommerce.domain.Product;
import org.ecommerce.domain.PhysicalProduct;
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
        Product product = new PhysicalProduct("testProductOne", 60.0, "testSku1",250);
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
    public void shouldReturnCreatedProduct() {
        Product product = new PhysicalProduct("testProductTwo", 60.0, "testSku2",251);

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
        Product product = new PhysicalProduct("", 60.0, "",252);

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
        Product product = new PhysicalProduct("testProductThree", 60.0, "duplSku",253);
        Product product2 = new PhysicalProduct("testProdTres", 60.0, "duplSku",253);
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
        Product product = new PhysicalProduct("dublName", 60.0, "testSku4",254);
        Product productTwo = new PhysicalProduct("dublName", 60.0, "testerSku4",254);

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
            Product product = new PhysicalProduct("testProductNum" + i, 1*i, "testSku" + i,30*i);
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
            Product product = new PhysicalProduct("testProductNo" + i, 1*i, "testSku" + i,30*i);
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
            Product product = new PhysicalProduct("testProductNo" + i, 1*i, "testSku" + i,30*i);
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

    @Test
    @TestTransaction
    public void shouldCreatePhysicalProduct() {
        PhysicalProduct phProduct = new PhysicalProduct("EspressoPh", 60.0, "espph", 250);
        given()
        .contentType(ContentType.JSON)
                .body(phProduct)
                .post("/products/physical")
                .then()
                .statusCode(201);

        given()
        .contentType(ContentType.JSON)
                .get("/products")
                .then()
                .statusCode(200)
                .body("weight", hasItem(250.0f)); // 250.0f  = test search for float


    }

    @Test
    @TestTransaction
    public void shouldCreateDigitalProduct() {
        DigitalProduct dgProduct = new DigitalProduct("BrewInstructiong",60.00,"brewIns","www.diakonbrew.com/digital/manual/brewinstructions",105.41);
        given()
        .contentType(ContentType.JSON)
                .body(dgProduct)
                .post("/products/digital")
                .then()
                .statusCode(201);
        given()
        .contentType(ContentType.JSON)
                .get("/products")
                .then()
                .statusCode(200)
                .body("fileSize", hasItem(105.41f));
    }
}
