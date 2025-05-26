package DataProviderPatternJava;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StudentRegistrationPage {
    private WebDriver driver;
    private final String URL = "https://demoqa.ru/qa-auto/forms";

    public StudentRegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public StudentRegistrationPage open() {
        driver.get(URL);
        return this;
    }

    public StudentRegistrationPage setFirstName(String value) {
        driver.findElement(By.id("firstName")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage setLastName(String value) {
        driver.findElement(By.id("lastName")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage setEmail(String value) {
        driver.findElement(By.id("email")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage selectGender(String gender) {
        driver.findElement(By.id("gender-" + gender)).click();
        return this;
    }

    public StudentRegistrationPage setMobile(String value) {
        driver.findElement(By.id("mobile")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage setDateOfBirth(String value) {
        driver.findElement(By.xpath("//button[contains(@class,'justify-start')]")).click();
        // Здесь можно добавить выбор даты, если потребуется
        return this;
    }

    public StudentRegistrationPage selectHobby(String hobby) {
        driver.findElement(By.id("hobby-" + hobby)).click();
        return this;
    }

    public StudentRegistrationPage uploadPicture(String filePath) {
        driver.findElement(By.id("picture-upload")).sendKeys(filePath);
        return this;
    }

    public StudentRegistrationPage setCurrentAddress(String value) {
        driver.findElement(By.id("currentAddress")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage setState(String value) {
        driver.findElement(By.id("state")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage setCity(String value) {
        driver.findElement(By.id("city")).sendKeys(value);
        return this;
    }

    public StudentRegistrationPage submit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return this;
    }
} 