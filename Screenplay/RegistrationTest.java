package Screenplay;

// Для работы Screenplay необходимы зависимости Serenity BDD и Selenium WebDriver в вашем проекте.
// Пример для Maven:
// <dependency>
//   <groupId>net.serenity-bdd</groupId>
//   <artifactId>serenity-screenplay</artifactId>
//   <version>3.6.12</version>
// </dependency>
// <dependency>
//   <groupId>org.seleniumhq.selenium</groupId>
//   <artifactId>selenium-java</artifactId>
//   <version>4.19.1</version>
// </dependency>

import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Value;
import net.serenitybdd.screenplay.actions.OpenUrl;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Upload;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;

@SerenityTest
public class RegistrationTest {
    @Managed
    WebDriver driver;

    Actor user = Actor.named("User");

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        user.can(BrowseTheWeb.with(driver));
    }

    @Test
    public void testSuccessfulRegistration() {
        user.attemptsTo(OpenUrl.of("https://demoqa.ru/qa-auto/forms"));
        user.attemptsTo(Enter.theValue("Иван").into(RegistrationForm.FIRST_NAME));
        user.attemptsTo(Enter.theValue("Петров").into(RegistrationForm.LAST_NAME));
        user.attemptsTo(Enter.theValue("ivan.petrov@example.com").into(RegistrationForm.EMAIL));
        user.attemptsTo(Click.on(RegistrationForm.GENDER_MALE));
        user.attemptsTo(Enter.theValue("9123456789").into(RegistrationForm.MOBILE));
        user.attemptsTo(SelectDate.of("2023-01-01"));
        user.attemptsTo(Click.on(RegistrationForm.HOBBY_SPORTS));
        user.attemptsTo(Click.on(RegistrationForm.HOBBY_MUSIC));
        user.attemptsTo(Enter.theValue("C:/Users/User/Downloads/test.jpg").into(RegistrationForm.PICTURE_UPLOAD));
        user.attemptsTo(Enter.theValue("г. Москва, ул. Пушкина, д. 1").into(RegistrationForm.CURRENT_ADDRESS));
        user.attemptsTo(Enter.theValue("Москва").into(RegistrationForm.STATE));
        user.attemptsTo(Enter.theValue("Москва").into(RegistrationForm.CITY));
        user.attemptsTo(Click.on(RegistrationForm.SUBMIT));

        // Пример проверки результата (можно доработать под структуру результата)
        String name = Text.of(Target.the("Имя").located(By.xpath("//p[contains(text(),'Имя')]"))).viewedBy(user).asString();
        assertThat(name).contains("Иван Петров");
        // Аналогично можно добавить проверки для других полей
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }
} 