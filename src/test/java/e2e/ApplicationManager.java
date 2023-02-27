package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;
    CreateContactHelpers createContact;
    EditContactHelpers editeContact;

    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }

    public CreateContactHelpers getCreateContact() {
        return createContact;
    }

    public EditContactHelpers getEditContact() {
        return editeContact;
    }

    protected void init() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        //***********Chrome*********** */
//        //capabilities.setBrowserName("chrome");
//        //capabilities.setVersion("76.0");
//        //***********FireFox***********/
//        capabilities.setBrowserName("firefox");
//        capabilities.setVersion("90.0");
//        //***********Opera*********** */
////        capabilities.setBrowserName("chrome");
////        capabilities.setVersion("62.0");
//        capabilities.setCapability("enableVNC", true);
//        capabilities.setCapability("enableLog", true);
//        driver = new RemoteWebDriver(
//                URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
//                capabilities);
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        createContact = new CreateContactHelpers(driver);
        editeContact = new EditContactHelpers(driver);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, screenshot);
        return screenshot.getAbsolutePath();
    }

    protected void stop() {
        driver.quit();
    }
}
