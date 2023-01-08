package com.restapi.EmployeeMicroservices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@SpringBootTest
class EmployeeMicroservicesApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Greeting message")
	public void getMsgTest() throws URISyntaxException {
		System.out.println("Test Started");
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/get_msg/meghal";
		URI uri = new URI(url);
		ResponseEntity<String> response = template.getForEntity(uri, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println(response.toString());
		System.out.println("Test Ended");
	}

	@Test
	@DisplayName("Show Employee By Id")
	public void findEmployeeByIdTest() throws URISyntaxException {
		System.out.println("Test Started");
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/find_employee_by_id/2";
		URI uri = new URI(url);
		ResponseEntity<String> response = template.getForEntity(uri, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println(response.toString());
		System.out.println("Test Ended");
	}

	@Test
	@DisplayName("List Of Employees")
	public void listOfAllEmployeesTest() throws URISyntaxException {
		System.out.println("Test Started");
		RestTemplate template = new RestTemplate();
		String url = "http://localhost:8080/find_all_employees";
		URI uri = new URI(url);
		ResponseEntity<String> response = template.getForEntity(uri, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println(response.toString());
		System.out.println("Test Ended");
	}

	@Test
	@DisplayName("Get Highest Salary Employee")
	public void getHighestSalaryEmployeeTest() throws URISyntaxException {
		System.out.println("Test started");
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/get_highest_salary_employee";
		URI uri = new URI(url);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println(response.toString());
		System.out.println("Test Ended");
	}

	@Test
	@DisplayName("Employee Deleted")
	public void deleteEmployee() throws URISyntaxException {
		System.out.println("Test Started");
		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8080/delete_employee/3";
		URI uri = new URI(url);
		ResponseEntity<Employee> response = restTemplate.postForEntity(uri, restTemplate, Employee.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println(response.toString());
		System.out.println("Test Ended");
	}
}
