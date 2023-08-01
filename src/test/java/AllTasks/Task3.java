package AllTasks;

import Assertion.Assertions_Task3;
import Payloads.Payloads_Task3;
import URLs.EndpointUrls;
import URLs.baseurl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Task3 {
    public static void main(String[] args) {
        AllTests();
    }

    @Test
    public static void AllTests(){
        System.out.println("TEST 1");
        test1();
        System.out.println("\n");
        System.out.println("TEST 2");
        test2();
        System.out.println("\n");
        /*System.out.println("TEST 3");
        test3();
        System.out.println("\n");*/
        System.out.println("TEST 4");
        test4();
        System.out.println("\n");
        System.out.println("TEST 5");
        test5();
    }

    public static void test1(){
        String apiUrl = (baseurl.base + EndpointUrls.list_users);

        System.out.println(apiUrl);

        Response response = get(apiUrl);

        int totalPages = response.jsonPath().getInt("total_pages");
        int totalRecords = response.jsonPath().getInt("total");

        System.out.println("Total Pages: " + totalPages);
        System.out.println("Total Records: " + totalRecords);

        Assertions_Task3.asserting1(totalPages, 2, "Total pages fetched are incorrect");
        Assertions_Task3.asserting1(totalRecords, 12, "Total records fetched are incorrect");

        System.out.println("Total pages and records are correct");
    }

    public static void test2(){
        String apiUrl = (baseurl.base + EndpointUrls.create_user);

        System.out.println(apiUrl);

        //String requestBody = "{\"name\":\"anas\",\"job\":\"QA\"}";
        System.out.println(Payloads_Task3.test2_payload);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(Payloads_Task3.test2_payload)
                .post(apiUrl);

        int employeeId = response.jsonPath().getInt("id");

        System.out.println("New Employee ID: " + employeeId);

        Assertions_Task3.asserting2(employeeId, "New employee ID is null or zero.");

        System.out.println("New employee ID has been assigned to the employee");
    }

    public static void test3(){
        String apiUrl = (baseurl.base + EndpointUrls.update_user);

        System.out.println(apiUrl);

        String requestBody = "{\"name\":\"mani\",\"job\"Software engineer\"}";
        System.out.println(Payloads_Task3.test3_payload);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(Payloads_Task3.test3_payload)
                .put(apiUrl);

        String responseBody = response.getBody().asString();

        System.out.println("Response body: " + responseBody);

        int statusCode = response.getStatusCode();
        //Assert.assertEquals(statusCode,200, "Status code does not match");

        String updatedName = response.jsonPath().getString("name");
        String updatedJob = response.jsonPath().getString("job");

        Assert.assertEquals(updatedName, "mani", "Name was not updated");
        Assert.assertEquals(updatedJob, "Software engineer");

    }

    public static void test4(){
        String apiUrl = (baseurl.base + EndpointUrls.update_user_patch);

        System.out.println(apiUrl);

        String requestBody = "{\"name\":\"Ichigo Kurosaki\"}";
        System.out.println(Payloads_Task3.test4_payload);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(Payloads_Task3.test4_payload)
                .patch(apiUrl);

        String responseBody = response.getBody().asString();

        System.out.println("Response Body: " + responseBody);

        int statusCode = response.getStatusCode();
        Assertions_Task3.asserting4_1(statusCode, 200);
        //Assert.assertEquals(statusCode, 200);

        String updatedName = response.jsonPath().getString("name");
        String updatedJob = response.jsonPath().getString("job");

        Assertions_Task3.asserting4_2(updatedName, "Ichigo Kurosaki", "Name was not updated");
        //Assertion.Assertions.asserting4_3(updatedJob, "Job should not be updated");

        System.out.println("Employee name successfully updated");

    }

    public static void test5(){
        String apiUrl = (baseurl.base + EndpointUrls.delete_user);

        System.out.println(apiUrl);

        Response response = given()
                .contentType(ContentType.JSON)
                .delete(apiUrl);

        int statusCode = response.getStatusCode();

        Assertions_Task3.asserting5(statusCode, 204, "Employee not successfully deleted");

        System.out.println("Employee has been deleted successfully");
    }
}








