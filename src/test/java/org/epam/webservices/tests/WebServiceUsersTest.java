package org.epam.webservices.tests;

import org.epam.webservices.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WebServiceUsersTest {

	private final static int STATUSCODE = 200;
	private final static String CONTENTTYPEVALUE = "application/json; charset=utf-8";
	private final static String CONTENTTYPEHEADER = "content-type";
	private final static int NUMOFUSERS = 10;

	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
	}

	@Test(priority = 0)
	public void checkStatusCode() {

		Response response = RestAssured.given().get("/users").andReturn();

		System.out.println(response.getStatusCode());

		Assert.assertEquals(response.getStatusCode(), STATUSCODE, "Actual and Expected Status codes are not same");

	}

	@Test(priority = 1)
	public void checkResponseHeader() {

		Response response = RestAssured.given().get("/users").andReturn();

		Assert.assertTrue(response.getHeaders().hasHeaderWithName(CONTENTTYPEHEADER),
				"The 'content-type' Header is not there in the reponse body");

		Assert.assertEquals(response.getHeader(CONTENTTYPEHEADER), CONTENTTYPEVALUE,
				"The 'content-type' Header value is not matched");

		System.out.println(response.getHeader(CONTENTTYPEHEADER));

	}

	@Test(priority = 2)
	public void checkResponseBody() {

		Response response = RestAssured.given().get("/users").andReturn();

		User[] users = response.as(User[].class);

		Assert.assertEquals(users.length, NUMOFUSERS, "Number of users is not matched");

		System.out.println(users.length);

	}

}
