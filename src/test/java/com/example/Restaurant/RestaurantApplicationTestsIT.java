package com.example.Restaurant;

import com.example.Restaurant.domain.Services.KitchenRegistrationService;
import com.example.Restaurant.entitys.Kitchen;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantApplicationTestsIT {
    @LocalServerPort
    private int port;

//    @Autowired
//    private Flyway flyway;

    @Before
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/kitchens";
//        flyway.migrate();
    }
@Test
public void returnStatus200OnGetKitchens(){
    RestAssured.given()
                    .port(port)
                    .basePath("/kitchens")
                    .accept(ContentType.JSON)
            .when()
                .get()
            .then()
             .statusCode(200);
}
@Test
    public void returnFourKitchens(){
        RestAssured.given()
                        .port(port)
                        .basePath("/kitchens")
                        .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .body("", Matchers.hasSize(29))
                    .body("name", Matchers.hasItems("Chinese")
                    );
    }
//@Test
//    public void mustReturnStatusCreated(){
//        RestAssured.given()
//                .body("{ " +
//                        "\"name\": \"Chinese\" " +
//                        "}")
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .when()
//                    .post()
//                .then()
//                .statusCode(201);
//    }

    //	@Autowired
//	KitchenRegistrationService kitchenRegistrationService;
//
//	@Test
//	void contextLoads() {
//		Kitchen newKitchen = new Kitchen();
//		newKitchen.setName("Chinese");
//		newKitchen = kitchenRegistrationService.add(newKitchen);
//		assertThat(newKitchen.getId()).isNotNull();
//	}
//	@Test
//	void testKitchenIfNoName(){
//		Kitchen newKitchen = new Kitchen();
//		newKitchen.setName("null");
//		newKitchen = kitchenRegistrationService.add(newKitchen);
//	}
//	@Test
//	public void testKitchenIfInUse(){
//		kitchenRegistrationService.exclude(1L);
//	}
//	@Test
//	public void testKitchenIfNotExists(){
//		kitchenRegistrationService.exclude(100L);
//	}
}
