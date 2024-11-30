package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class StepDefination extends Utils {
    private RequestSpecification res;
    Response response;
    private ResponseSpecification resResponse;
    TestDataBuild data = new TestDataBuild();

    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException {

        res=given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
    }
    @When("user calls {string} with {string} http request")
    public void user_calls_with_http_request(String resource, String method) {

        APIResources resourceAPI=APIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());

        resResponse=new ResponseSpecBuilder().
                expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
        response=res.when().post(resourceAPI.getResource());
        else if (method.equalsIgnoreCase("GET"))
            response=res.when().get(resourceAPI.getResource());
    }
    @Then("the API call got success with status code {int}")
    public void the_api_call_got_success_with_status_code(Integer int1) {
       assertEquals(response.getStatusCode(),200);

    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String keyValue, String ExpectedValue) {
        // Write code here that turns the phrase above into concrete actions

       assertEquals(getJsonPath(response,keyValue), ExpectedValue);
    }
    @Then("Verify the place_Id create maps to {string} using {string}")
    public void verify_the_place_id_create_maps_to_using(String expectedName, String resource) throws IOException {
        // Write code here that turns the phrase above into concrete actions

        String placeId=getJsonPath(response,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",placeId);
        user_calls_with_http_request(resource,"GET");

        String actualName=getJsonPath(response,"name");
        assertEquals(actualName, expectedName);
    }
}