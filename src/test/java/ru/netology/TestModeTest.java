package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.UserGenerator.*;

public class TestModeTest {

    SelenideElement form = $x("//form");
    SelenideElement error = $x("//div[@data-test-id='error-notification']");

    @BeforeEach
    public void setup() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyActiveUser() {
        UserData userActive = UserGenerator.generateUser("active");
        UserRegistration.registration(userActive);
        form.$x(".//span[@data-test-id='login']//input").val(userActive.getLogin());
        form.$x(".//span[@data-test-id='password']//input").val(userActive.getPassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        $x("//h2").should(text("Личный кабинет"));
    }

    @Test
    public void activeUserBadLogin() {
        UserData userActive = UserGenerator.generateUser("active");
        UserRegistration.registration(userActive);
        form.$x(".//span[@data-test-id='login']//input").val(generateLogin());
        form.$x(".//span[@data-test-id='password']//input").val(userActive.getPassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        error.should(visible, Duration.ofSeconds(15));
        error.$x(".//div[@class='notification__content']").should(text("Неверно указан логин или пароль"));
        error.$x(".//button").click();
        error.should(hidden);
    }

    @Test
    public void activeUserBadPassword() {
        UserData userActive = UserGenerator.generateUser("active");
        UserRegistration.registration(userActive);
        form.$x(".//span[@data-test-id='login']//input").val(userActive.getLogin());
        form.$x(".//span[@data-test-id='password']//input").val(generatePassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        error.should(visible, Duration.ofSeconds(15));
        error.$x(".//div[@class='notification__content']").should(text("Неверно указан логин или пароль"));
        error.$x(".//button").click();
        error.should(hidden);
    }

    @Test
    public void blockUser() {
        UserData userBlocked = UserGenerator.generateUser("blocked");
        UserRegistration.registration(userBlocked);
        form.$x(".//span[@data-test-id='login']//input").val(userBlocked.getLogin());
        form.$x(".//span[@data-test-id='password']//input").val(userBlocked.getPassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        error.should(visible, Duration.ofSeconds(15));
        error.$x(".//div[@class='notification__content']").should(text("Пользователь заблокирован"));
        error.$x(".//button").click();
        error.should(hidden);
    }

    @Test
    public void blockUserBadLogin() {
        UserData userBlocked = UserGenerator.generateUser("blocked");
        UserRegistration.registration(userBlocked);
        form.$x(".//span[@data-test-id='login']//input").val(generateLogin());
        form.$x(".//span[@data-test-id='password']//input").val(userBlocked.getPassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        error.should(visible, Duration.ofSeconds(15));
        error.$x(".//div[@class='notification__content']").should(text("Неверно указан логин или пароль"));
        error.$x(".//button").click();
        error.should(hidden);
    }

    @Test
    public void blockUserBadPassword() {
        UserData userBlocked = UserGenerator.generateUser("blocked");
        UserRegistration.registration(userBlocked);
        form.$x(".//span[@data-test-id='login']//input").val(userBlocked.getLogin());
        form.$x(".//span[@data-test-id='password']//input").val(generatePassword());
        form.$x(".//button[@data-test-id='action-login']").click();
        error.should(visible, Duration.ofSeconds(15));
        error.$x(".//div[@class='notification__content']").should(text("Неверно указан логин или пароль"));
        error.$x(".//button").click();
        error.should(hidden);
    }
}
