package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class EditContactInfoTest extends TestBase {

    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void changeEditContactInfo(String lastName, String description) {
        String firstName = "Mario";
        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getContact().changeLanguage();
        app.getContact().fillFilterField(firstName);
//        app.getContact().checkCountRows(expectedCountRow);
        app.getContact().openContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
        app.getContact().descriptionEditSave(lastName, description);
        app.getContact().goToContactPage();
        app.getContact().fillFilterField(firstName);
//        app.getContact().checkCountRows(expectedCountRow);
        app.getContact().openContact();
        app.getContact().checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);
    }

}
