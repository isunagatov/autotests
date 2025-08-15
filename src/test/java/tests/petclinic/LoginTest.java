package tests.petclinic;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;
import pages.LoginPage;
import tests.TestBase;

import java.net.MalformedURLException;

public class LoginTest extends TestBase {
        @Test(enabled = true)
    public void login() throws MalformedURLException {
        String testName = "Базовый тест. Авторизация по Логину/паролю.";
        TestBase.openUrl(testName);
        LoginPage.login("user");
        Selenide.sleep(1000);
        LoginPage.logoutButton.click();
        Selenide.sleep(1000);
    }
}
