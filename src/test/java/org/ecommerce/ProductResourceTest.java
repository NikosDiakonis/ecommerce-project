    package org.ecommerce;

    import io.quarkus.test.TestTransaction;
    import io.quarkus.test.junit.QuarkusTest;
    import io.restassured.http.ContentType;
    import jakarta.transaction.Transactional;
    import org.ecommerce.domain.DigitalProductEntity;
    import org.ecommerce.domain.Discount;
    import org.ecommerce.domain.PhysicalProductEntity;
    import org.ecommerce.domain.ProductEntity;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.params.ParameterizedTest;
    import org.junit.jupiter.params.provider.Arguments;
    import org.junit.jupiter.params.provider.MethodSource;

    import java.util.stream.Stream;

    import static io.restassured.RestAssured.given;
    import static org.hamcrest.Matchers.equalTo;
    import static org.hamcrest.Matchers.hasItem;
    import static org.hamcrest.core.Is.is;
    import static org.junit.jupiter.api.Assertions.assertEquals;

    @QuarkusTest
    public class ProductResourceTest {
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
        public void shouldReturn400WhenEmptyName(){
            ProductEntity productEntity = new PhysicalProductEntity("", 60.0, "RandomTest",252);

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
        public void shouldReturn400WhenEmptySKU(){
            ProductEntity productEntity = new PhysicalProductEntity("RandomTest", 60.0, "",252);

            given()
                    .contentType(ContentType.JSON)
                    .body(productEntity)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(400);


        }

        @ParameterizedTest
        @MethodSource("duplicateProducts")
        @TestTransaction
        void testReturn400WhenDuplicateProduct(ProductEntity original, ProductEntity dublicate) {
            given()
                    .contentType(ContentType.JSON)
                    .body(original)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(201);

            given()
                    .contentType(ContentType.JSON)
                    .body(dublicate)
                    .when()
                    .post("/products")
                    .then()
                    .statusCode(400);
        }
        static Stream<Arguments> duplicateProducts() {
            return Stream.of(
                    Arguments.of( //dublicate Name
                             new PhysicalProductEntity("dublName", 60.0, "testSkufour",254),
                             new PhysicalProductEntity("dublName", 60.0, "testerSku4",254)
                    ),
                    Arguments.of(// dublicate sku
                            new PhysicalProductEntity("doubleName",60,"sku4",254),
                            new PhysicalProductEntity("dblName",60,"sku4",254)
                    )
            );
        }


        @Test
        @TestTransaction
        public void shouldTestPagination() {
            for(int i = 1; i <= 14; i++) {
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
            for(int i = 1; i <= 14; i++) {
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
                    .body("[0].name", equalTo("testProductNo1"));

        }
        @Test
        @TestTransaction
        public void shouldTestSortingByPrice() {
            for(int i = 1; i <= 14; i++) {
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
                    .body("[0].price", equalTo(1.0f));


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
