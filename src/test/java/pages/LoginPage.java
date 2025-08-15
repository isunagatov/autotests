package pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import tests.TestBase;
import utils.TestData;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class LoginPage extends TestBase {

    public static SelenideElement userNameField = $("#input-vaadin-text-field-14");
    public static SelenideElement showPassword = $("#vaadinLoginPassword > vaadin-password-field-button");
    public static SelenideElement passwordField = $(By.id("vaadinLoginPassword"));
    public static SelenideElement signInButton = $("#vaadinLoginFormWrapper > vaadin-button:nth-child(2)");
    public static SelenideElement logoutButton = $("#logoutButton");

    public static void login(String Login, String Password){
        step("Авторизация");
        LoginPage.userNameField.setValue(Login);
        LoginPage.passwordField.setValue(Password);
        LoginPage.signInButton.click();
        sleep(4000);
    }
    @Step("Авторизация по логину")
    public static void login(TestData.UserClass.User user){
        step("Заполнить поле Логин значением: " + user.getUserLogin() + " .");
        LoginPage.userNameField.setValue(user.getUserLogin());
        LoginPage.passwordField.click();
        step("Нажать кнопку отображения пароля");
        LoginPage.showPassword.click();
        step("Заполнить поле Пароль значением пароля, предварительно очистив поле.");
        LoginPage.passwordField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        LoginPage.passwordField.sendKeys(Keys.DELETE);

        LoginPage.passwordField.sendKeys(user.getUserPassword());
        step("Нажать кнопку входа.");
        LoginPage.signInButton.click();
        sleep(2000);
    }
    @Step("Авторизация по логину")
    public static void login(String UserRoleName){
        TestData.UserClass.User User = TestData.UserClass.getUserByRole("Petclinic", UserRoleName);
        login(User);
    }
    @Step("Выход из системы")
    public static void logOut(){
        LoginPage.logoutButton.click();
    }
}

