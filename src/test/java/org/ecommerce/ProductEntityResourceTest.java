    package org.ecommerce;

    import io.quarkus.test.TestTransaction;
    import io.quarkus.test.junit.QuarkusTest;
    import io.restassured.http.ContentType;
    import jakarta.transaction.Transactional;
    import org.ecommerce.domain.*;
    import org.ecommerce.domain.PhysicalProductEntity;
    import org.ecommerce.domain.ProductEntity;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;

    import static io.restassured.RestAssured.given;
    import static org.hamcrest.Matchers.equalTo;
    import static org.hamcrest.Matchers.hasItem;
    import static org.hamcrest.core.Is.is;
    import static org.junit.jupiter.api.Assertions.assertEquals;

    @QuarkusTest
    public class ProductEntityResourceTest {
        @BeforeEach
        @Transactional
        public void cleanDatabase() {
            Discount.deleteAll();   // children first
            ProductEntity.deleteAll();    // then parents
        }

        @Test
        @TestTransaction
        public void shouldReturn201() {
            ProductEntity productEntity = new PhysicalProductEntity("testProductOne", 60.0, "testSku1",250);
            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
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
                        .statusCode(200)
                    .body("isEmpty()", is(true));

        }
        @Test
        @TestTransaction
        public void shouldReturnCreatedProduct() {
            ProductEntity productEntity = new PhysicalProductEntity("testProductTwo", 60.0, "testSku2",251);

            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
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
            ProductEntity productEntity = new PhysicalProductEntity("", 60.0, "",252);

            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(400);


        }

        @Test
        @TestTransaction
        public void shouldReturn400WhenDuplicateSku(){
            ProductEntity productEntity = new PhysicalProductEntity("testProductThree", 60.0, "duplSku",253);
            ProductEntity productEntity2 = new PhysicalProductEntity("testProdTres", 60.0, "duplSku",253);
            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);

                    given()
                            .contentType(ContentType.JSON)
                            .body(productEntity2)
                            .when()
                            .post("/products")
                            .then()
                            .statusCode(400);

        }
        //TODO: Merge with the above test (data driven tests)
        @Test
        @TestTransaction
        public void shouldReturn400WhenDuplicateName(){
            ProductEntity productEntity = new PhysicalProductEntity("dublName", 60.0, "testSku4",254);
            ProductEntity productEntityTwo = new PhysicalProductEntity("dublName", 60.0, "testerSku4",254);

            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);
            given()
            .contentType(ContentType.JSON)
                    .body(productEntityTwo)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(400);
        }

        @Test
        @TestTransaction
        public void shouldTestPagination() {
            for(int i = 0; i <= 14; i++) {
                ProductEntity productEntity = new PhysicalProductEntity("testProductNum" + i, 1*i, "testSku" + i,30*i);
                given()
                .contentType(ContentType.JSON)
                        .body(productEntity)
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
                ProductEntity productEntity = new PhysicalProductEntity("testProductNo" + i, 1*i, "testSku" + i,30*i);
                given()
                        .contentType(ContentType.JSON)
                        .body(productEntity)
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
                ProductEntity productEntity = new PhysicalProductEntity("testProductNo" + i, 1*i, "testSku" + i,30*i);
                given()
                        .contentType(ContentType.JSON)
                        .body(productEntity)
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
            PhysicalProductEntity phProduct = new PhysicalProductEntity("EspressoPh", 60.0, "espph", 250);
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
            DigitalProductEntity dgProduct = new DigitalProductEntity("BrewInstructiong",60.00,"brewIns","www.diakonbrew.com/digital/manual/brewinstructions",105.41);
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

        @Test
        @TestTransaction
        public void shouldReturnProductFinalPrice() {
            PhysicalProductEntity espresso = new PhysicalProductEntity("EspressoNew", 60.0, "espnew", 250);
            Discount fixedDiscount = new Discount();
            fixedDiscount.discountType = "FIXED";
            fixedDiscount.discountValue = 10;
            fixedDiscount.productEntity = espresso;
            espresso.discounts.add(fixedDiscount);
           Integer id = given()
                    .contentType(ContentType.JSON)
                    .body(espresso)
                    .post("/products/physical")
                    .then()
                    .statusCode(201)
                    .extract().path("id");


            // REST-assured parses plain JSON numbers without explicit type info.
            // Extracting as Double.class ensures a robust type comparison
            // to avoid AssertionError due to type mismatch (Double vs Float/BigDecimal).
            Double finalPrice = given()
                    .contentType(ContentType.JSON)
                    .get("/products/" + id + "/price?quantity=3")
                    .then()
                    .statusCode(200)
                    .extract().as(Double.class);

            assertEquals(150.0, finalPrice);


        }
    }
