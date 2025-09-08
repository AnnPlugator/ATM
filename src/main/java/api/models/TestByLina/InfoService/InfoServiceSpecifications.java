package api.models.InfoService;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class InfoServiceSpecifications {

    public static RequestSpecification requestSpec (String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static ResponseSpecification responseSpecOk200(){
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    public static void usingSpecification(RequestSpecification request,
                                          ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
