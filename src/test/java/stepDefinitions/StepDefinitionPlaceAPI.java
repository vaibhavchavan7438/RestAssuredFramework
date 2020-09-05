package stepDefinitions;

import io.cucumber.java.en.Given;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utility.Util;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import actaulPayload.ActualPayload;

public class StepDefinitionPlaceAPI extends Util {

	RequestSpecification preResponse;
	ResponseSpecification postResponse;
	Response response;
	ActualPayload actualPayload = new ActualPayload();
	
	static String place_id;
	String resource;

	@Given("Add place API with {string}, {string} and {string} parameters")
	public void add_place_api_with_and_parameters(String name, String address, String language) throws IOException {
		/*
		 * Response response=given() .queryParam("key",
		 * "qaclick123").header("content-type","application/json") .body("")
		 * .when().post("") .then().assertThat().statusCode(200).extract().response();
		 */
		System.out.println(name + " " + address + " " + language);
		preResponse = given().spec(requestSpec()).body(actualPayload.AddPlace(name, address, language));
	}

	@When("API {string} is fired with {string} http request")
	public void api_is_fired_with_http_request(String apiName, String httpMethod) throws IOException {

		System.out.println("------------------"+apiName+"-----------------");
		System.out.println("resource: "+getProperty(apiName));
		
		resource=getProperty(apiName).trim();

		postResponse = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

		if (httpMethod.equalsIgnoreCase("POST")) {
			response = preResponse.when().post(resource);
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			response = preResponse.when().get(resource);
		}

		
	}

	@Then("success response with status code {int}")
	public void success_response_with_status_code(Integer statusCode) {
		// postResponse=responseSpec();
		// assert.assertEquals(response.getStatusCode(),"200");

		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} is equal to {string}")
	public void is_equal_to(String key, String expectedValue) {
	    // Write code here that turns the phrase above into concrete actions
		place_id=getJsonPath("place_id", response);
		//System.out.println("place_id " +place_id);
		
	    assertEquals(getJsonPath(key, response), expectedValue);
		
	}
	
	@Given("getPlaceAPI with place_id key")
	public void get_place_api_with_place_id_key() throws IOException {
	 
		preResponse=given().spec(requestSpec()).queryParam("place_id", place_id);
	}

}
