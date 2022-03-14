package com.techfios;

import org.apache.log4j.BasicConfigurator;
import org.kohsuke.rngom.parse.host.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestTest {
	String BaseUrl = "https://simple-books-api.glitch.me/";

	Logger LOG = LoggerFactory.getLogger(GetRequestTest.class);

	Response response1;
	Response response2;
	Response response3;
	Response response4;
	Response response5;

	/**
	 * @apiNote Posting data to API
	 * @param option Specifies the type of request
	 */
	public void RequestToApi(String option) {

		if (option.equalsIgnoreCase("post")) {
			String body = "{\"bookId\": 1, \"customerName\": \"John\" }";
			response4 = RestAssured.given()
					.header("Authorization", "Bearer ca953c93d148c0749bf162a5164437f92ffb5aa4104021adfba4276703add443")
					.header("content-type", "application/json").body(body).post(BaseUrl + "orders/");
		} else if (option.equalsIgnoreCase("get")) {
			response4 = RestAssured.given()
					.header("Authorization", "Bearer ca953c93d148c0749bf162a5164437f92ffb5aa4104021adfba4276703add443")
					.header("content-type", "application/json").get(BaseUrl + "orders/V1gIR59uZ4UtofAfNKs9T");
		} else if (option.equalsIgnoreCase("register")) {
			String registrationBody = "{\"clientName\": \"Nerdy's Desktop\", \"clientEmail\": \"nerdy@example.com\" }";
			response3 = RestAssured.given().header("content-type",
					"application/json").body(registrationBody).post(BaseUrl + "api-clients").andReturn();
		} else if (option.equalsIgnoreCase("update")) {
			String body = "{\"customerName\": \"Nerdy\" }";
			response4 = RestAssured.given()
					.header("Authorization", "Bearer ca953c93d148c0749bf162a5164437f92ffb5aa4104021adfba4276703add443")
					.header("content-type", "application/json").body(body).patch(BaseUrl + "orders/V1gIR59uZ4UtofAfNKs9T");
		}

	}

	@Test
	public void testGetAllStudentRecords() {
		BasicConfigurator.configure();

		/**
		 * Example 1 - GET all the existing student's record
		 */
		// LOG.info("Step - 1 : Send GET Request");
		// Response response = RestAssured.given().get(BaseUrl + "status").andReturn();
		// Response response1 = RestAssured.given().get(BaseUrl +
		// "books?limit=1").andReturn();

		LOG.info("Step - 2 : Print the JSON response body");

		// response1 = RestAssured.given().get(BaseUrl + "books/1").andReturn();
		// response1.getBody().prettyPrint();
		RequestToApi("update");
		response4.getBody().prettyPrint();

		// LOG.info("Step - 3 : Assert StatusCode = 200");
		// Assert.assertEquals(response.getStatusCode(), 200, "http status code");

		// LOG.info("Step - 4 : Verify that the response contains id = 101");
		// LOG.info("list of Student's Id " +response.getBody().jsonPath());
		// Assert.assertTrue(response.getBody().jsonPath().getList("id").contains(23));
	}
}