package e2e.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");


    //before
    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    //test
    @Test
    public void locators() {
        driver.findElement(By.name("email")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail.com");
//        driver.findElement(By.name("password")).sendKeys("test@gmail.com");
        driver.findElement(By.cssSelector("/"));

        driver.findElement(By.id("firstname"));
        driver.findElement(By.cssSelector("#firstname input"));

        driver.findElement(By.tagName("p"));
        driver.findElement(By.cssSelector("p"));
        driver.findElement(By.xpath("//*[@id=\"registration-form\"]/div[1]/div[1]/div/input"));
    }

    @Test
    public void registerNewUser() {
        String userData = "test3@gmail.com";

        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        fillField(userData, By.cssSelector("[ng-reflect-name=\"confirm_password\"]"));
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }

    private void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    //after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
    }
}
