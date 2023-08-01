package AllTasks;

import URLs.EndpointUrls;
import URLs.baseurl;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.equalTo;

public class Task4_Part_2_and_3 {
    @Test
    public static void Task4() {
        System.out.println("TEST 2");
        task2();
        System.out.println("\n");
        System.out.println("TEST 3");
        task3();
    }

    public static void task2(){
        Response response = given()
                .when()
                .get(baseurl.base + EndpointUrls.list_users);

        ArrayList<Integer> userIdsList = response.jsonPath().get("data.id");
        int[] userIds = new int[userIdsList.size()];
        for (int i = 0; i < userIdsList.size(); i++) {
            userIds[i] = userIdsList.get(i);
        }

        for (int userId : userIds) {
            Response userDetailsResponse = given()
                    .pathParam("id", userId)
                    .when()
                    .get(baseurl.base + EndpointUrls.delete_user_with_id_task2);

            userDetailsResponse.then().assertThat().statusCode(200);

            userDetailsResponse.then().assertThat().body("data.id", equalTo(userId));
            userDetailsResponse.then().assertThat().body("data.email", instanceOf(String.class));
            userDetailsResponse.then().assertThat().body("data.first_name", instanceOf(String.class));
            userDetailsResponse.then().assertThat().body("data.last_name", instanceOf(String.class));
            userDetailsResponse.then().assertThat().body("data.avatar", instanceOf(String.class));
        }
        System.out.println("All assertions passed with the right status code.");
    }

    public static void task3(){
        Response response = given()
                .when()
                .get(baseurl.base + EndpointUrls.list_users);

        Object user = response.jsonPath().get("data.find { it.id == 12 }");
        System.out.println(user);

        if (user != null) {
            deleteScenario(user);
        }
    }
    public static void deleteScenario(Object user) {
        int userId = (int) ((java.util.Map<?, ?>) user).get("id");
        Response deletingResponse = given()
                .when()
                .delete((baseurl.base + EndpointUrls.delete_user_with_id_task3), userId);

        deletingResponse.then().assertThat().statusCode(204);
        System.out.println("Deletion Successful");
    }
}
