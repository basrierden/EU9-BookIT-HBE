package com.bookit.step_definitions;

import com.bookit.pages.SelfPage;
import com.bookit.pages.SignInPage;
import com.bookit.pages.TopNavigationBar;
import com.bookit.utilities.BrowserUtils;
import com.bookit.utilities.ConfigurationReader;
import com.bookit.utilities.Driver;
import com.bookit.utilities.Environment;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class SelfTest_StepDefinitions {

    public String teacherToken;
    public Response teachersMeResponse;
    public Response postResponse;

    public String studentEmail;
    public String studentPassword;

    public String studentFirstName;
    public String studentLastName;
    public String studentTeam;
    public String studentRole;
    public String studentCampus;


    TopNavigationBar topNavigationBar=new TopNavigationBar();
    SelfPage selfPage=new SelfPage();
    SignInPage signInPage=new SignInPage();

    WebDriverWait wait=new WebDriverWait(Driver.get(),10);


    @Given("I logged BookIt API using {string} and {string}")
    public void i_logged_BookIt_API_using_and(String email, String password) {

        Response teacherResponse = given().accept(ContentType.JSON)
                .and().queryParam("email", email)
                .and().queryParam("password", password)
                .and().get(Environment.BASE_URL + "sign")
                .then().extract().response();

        String token= teacherResponse.jsonPath().getString("accessToken");

        teacherToken= "Bearer " + token;
        System.out.println("teacherToken = " + teacherToken);
    }

    @When("I get the current user information from BookIt API")
    public void i_get_the_current_user_information_from_BookIt_API() {

            teachersMeResponse = given().accept(ContentType.JSON)
                .and().header("Authorization", teacherToken)
                .and().get(Environment.BASE_URL + "api/teachers/me")
                .then().statusCode(200)
                .extract().response();

    }

    @When("I send POST request to BookIt API with following information")
    public void i_send_POST_request_to_BookIt_API_with_following_information (Map<String,String> studentInformation) {

        studentEmail= (String) studentInformation.get("email");
        studentPassword= (String) studentInformation.get("password");

        studentFirstName=studentInformation.get("first-name");
        studentLastName=studentInformation.get("last-name");
        studentTeam=studentInformation.get("team-name");
        studentRole=studentInformation.get("role");
        studentCampus=studentInformation.get("campus-location");


        postResponse = given().accept(ContentType.JSON)
                .and().queryParams(studentInformation)
                .and().log().all()
                .and().header("Authorization", teacherToken)
                .when().post(Environment.BASE_URL + "api/students/student");


    }

    @When("API student status code should be {int}")
    public void api_student_status_code_should_be(Integer int1) {

        assertEquals(201, postResponse.statusCode());

    }




    @When("API teacher status code should be {int}")
    public void api_teacher_status_code_should_be(int int1) {

        assertEquals(int1, teachersMeResponse.statusCode());
    }




    @When("user logs in using BookIt web site using student email {string} and password {string}")
    public void user_logs_in_using_BookIt_web_site_using_student_email_and_password(String email, String password ) {


        Driver.get().get(Environment.URL);
        Driver.get().manage().window().maximize();
        BrowserUtils.waitFor(3);
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        BrowserUtils.waitFor(2);
        signInPage.signInButton.click();
        BrowserUtils.waitFor(3);
    }


    @And("user logs in using BookIt web site using {string} and {string}")
    public void user_logs_in_using_BookIt_web_site_using_and(String email, String password) {

        Driver.get().get(Environment.URL);
        Driver.get().manage().window().maximize();
        BrowserUtils.waitFor(3);
        signInPage.email.sendKeys(email);
        signInPage.password.sendKeys(password);
        BrowserUtils.waitFor(2);
        signInPage.signInButton.click();
        BrowserUtils.waitFor(5);
    }

    @When("user goes to the my self page")
    public void user_goes_to_the_my_self_page() {

        wait.until(ExpectedConditions.visibilityOf(topNavigationBar.my));
        topNavigationBar.my.click();
        topNavigationBar.self.click();
        BrowserUtils.waitFor(3);
    }

    @Then("UI and API teacher information must be match")
    public void ui_and_API_teacher_information_must_be_match() {

        String teacherFirstName= teachersMeResponse.jsonPath().getString("firstName");
        String teacherLastName= teachersMeResponse.jsonPath().getString("lastName");

        String expectedTeacherFullName= teacherFirstName + " " + teacherLastName;
        String expectedTeacherRole= teachersMeResponse.jsonPath().getString("role");

        BrowserUtils.waitFor(3);
        String actualTeacherName= selfPage.name.getText();
        String actualRole=selfPage.role.getText();

        assertEquals(expectedTeacherFullName, actualTeacherName);
        assertEquals(expectedTeacherRole,actualRole);

    }

    @Then("UI and API user information must be match")
    public void ui_and_API_user_information_must_be_match() {

        String expectedStudentFullName= studentFirstName+ " " +studentLastName;
        String expectedStudentTeam=studentTeam;
        String expectedStudentRole=studentRole;
        String expectedStudentCampus=studentCampus;

        String actualStudentFullName=selfPage.name.getText();
        String actualStudentTeam=selfPage.team.getText();
        String actualStudentRole=selfPage.role.getText();
        String actualStudentCampus=selfPage.campus.getText();


        assertEquals(expectedStudentFullName,actualStudentFullName);
        assertEquals(expectedStudentTeam,actualStudentTeam);
        assertEquals(expectedStudentRole,actualStudentRole);
        assertEquals(expectedStudentCampus,actualStudentCampus);


    }


}
