package api.tests;

import api.enums.EndPoint;
import api.model.contact.ContactDto;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNgTest extends ApiBase{
    ContactDto contactDto;
    Response response;

    int id;

    @BeforeMethod
    public void precondition(){
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
        System.out.println("Этот метод будет выполнен перед каждым тестом");
    }

    @AfterMethod
    public void afterMethod() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
        System.out.println("Этот метод будет выполнен после каждого теста");
    }

    @BeforeTest
    public void beforeTest() {
        System.out.println("Этот метод будет выполнен один раз перед первым запущенным тестом");
    }

    @AfterTest
    public void afterTest() {
        System.out.println("Этот метод будет выполнен один раз после последнего запущенного теста");
    }

    @BeforeGroups(groups = "name")
    public void beforeGroups() {
        contactDto = createContact();
        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto);
        id = response.jsonPath().getInt("id");
        System.out.println("Этот метод будет выполнен перед каждым тестом");
    }
    @AfterGroups(groups = "name")
    public void afterGroups() {
        doDeleteRequest(EndPoint.DELETE_CONTACT, 200, id);
        System.out.println("Этот метод будет выполнен после каждого теста");
    }
    @Test(groups= {"name"}, priority = 3)
    public void firstName() {
        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());
    }

    @Test(groups= {"name"}, priority = 1)
    public void lastName() {
        Assert.assertEquals(response.jsonPath().getString("lastName"), contactDto.getLastName());
    }

    @Test(priority = 1, invocationCount = 5)
    public void description() {
        Assert.assertEquals(response.jsonPath().getString("description"), contactDto.getDescription());
    }
    @Test(dependsOnMethods = "description")
    public void dependedTest() {
        System.out.println("Этот тест выполнится только если тест description не упадет");

    }

    @Test(enabled= false)
    public void ignoredTest() {
        System.out.println("");
    }

    @Test(invocationCount = 5)
    public void invocationCount() {
        System.out.println("Этот тест выполнится 5 раз");
    }

    @Test(expectedExceptions = {ArithmeticException.class })
    public void expectedExceptions() {
        int x = 4 / 0;
        System.out.println(x);
    }

    @Test
    @Parameters({"firstName", "lastName"})
    public void createContactTestParam(String firstName, String lastName) {
        ContactDto contactDto1 = new ContactDto();
        contactDto1.setFirstName(firstName);
        contactDto1.setLastName(lastName);
        contactDto1.setDescription("desc");

        response = doPostRequest(EndPoint.ADD_NEW_CONTACT, 201, contactDto1);
        id = response.jsonPath().getInt("id");

        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto1.getFirstName());
    }



}
