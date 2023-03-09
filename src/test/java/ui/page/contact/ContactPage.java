package ui.page.contact;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.page.PageBase;

@Getter
public class ContactPage extends PageBase {
    public ContactPage(WebDriver driver) {
        super(driver);
    }

    private final String BASE_PAGE_URL = "http://phonebook.telran-edu.de:8080/contacts/";

    @FindBy(xpath = "//ul[@role=\"tablist\"]/li[@ng-reflect-_id=1]")
    private WebElement infoTab;

    @FindBy(xpath = "//ul[@role=\"tablist\"]/li[@ng-reflect-_id=2]")
    private WebElement phoneTab;

    @FindBy(xpath = "//ul[@role=\"tablist\"]/li[@ng-reflect-_id=3]")
    private WebElement emailTab;

    @FindBy(xpath = "//ul[@role=\"tablist\"]/li[@ng-reflect-_id=4]")
    private WebElement addressTab;

}
