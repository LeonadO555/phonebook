package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {

    By messageBlock = By.xpath("//div[@class='toast-body']");
    By homePage = By.xpath("//a[@class='navbar-brand']//*[name()='svg']");
    By searchWindow = By.xpath("//input[@id='input-search-contact']");
    By groupList = By.xpath("//div[@id='contacts-list']//div[@class='list-group']");
    By groupListTap = By.xpath("//div[@id='contacts-list']//button[1]");
    By editContact = By.id("btn-edit-contact");
    By descriptionWindow = By.xpath("//textarea[@name='input-ec-description']");
    By lastNameWindow = By.xpath("//input[@name='input-ec-lastName']");
    By saveEditContact = By.xpath("//button[normalize-space()='Save']");

    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguage() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }

    public void openAddNewContact() {
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    public void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    public void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual description is not equal expected description");
    }

    public void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    public void goToContactPage() {
        driver.findElement(homePage).click();
    }

    public void fillFilterField(String first_name) {
        fillField(first_name, searchWindow);
    }

    public void openContact() {
        driver.findElement(groupListTap).click();
    }

    public void descriptionEditSave(String dataField, String dataField2) {
        driver.findElement(editContact).click();
        fillField(dataField, lastNameWindow);
        fillField(dataField2, descriptionWindow);
        driver.findElement(saveEditContact).click();
    }

}
