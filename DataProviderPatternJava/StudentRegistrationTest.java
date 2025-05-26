package DataProviderPatternJava;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class StudentRegistrationTest {
    @ParameterizedTest
    @MethodSource("DataProviderPatternJava.TestData#validData")
    void testSuccessfulRegistration(String firstName, String lastName, String email, String gender, String mobile, String dateOfBirth, String[] hobbies, String picture, String address, String state, String city) {
        WebDriver driver = new FirefoxDriver();
        StudentRegistrationPage page = new StudentRegistrationPage(driver);
        try {
            page.open()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(gender)
                .setMobile(mobile)
                .setDateOfBirth(dateOfBirth);
            for (String hobby : hobbies) {
                page.selectHobby(hobby);
            }
            page.uploadPicture(picture)
                .setCurrentAddress(address)
                .setState(state)
                .setCity(city)
                .submit();
            // Здесь можно добавить проверки результата
        } finally {
            driver.quit();
        }
    }
} 