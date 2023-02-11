package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class CreateContactTest extends TestBase {

    Faker faker = new Faker();

    //
    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void createNewContactDataProvider(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getContact().changeLanguage();
        app.getContact().openAddNewContact();
        app.getContact().fillAddNewContactForm(firstName, lastName, description);
        app.getContact().saveNewContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        app.getContact().goToContactPageAndFillFilterField(firstName);
        app.getContact().checkCountRows(expectedCountRow);
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactDataProviderWithFileCSV(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getContact().changeLanguage();
        app.getContact().openAddNewContact();
        app.getContact().fillAddNewContactForm(firstName, lastName, description);
        app.getContact().saveNewContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        app.getContact().goToContactPageAndFillFilterField(firstName);
        app.getContact().checkCountRows(expectedCountRow);
    }
}