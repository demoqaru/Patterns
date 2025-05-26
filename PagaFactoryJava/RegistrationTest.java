import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RegistrationTest {
    private WebDriver driver;
    private RegistrationPage page;
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
        page = new RegistrationPage(driver);
    }
    @Test
    public void testSuccessfulRegistration() {
        page.open()
            .setFirstName("Иван")
            .setLastName("Петров")
            .setEmail("ivan.petrov@example.com")
            .selectGender()
            .setMobile("9123456789")
            .setDateOfBirth("2023-01-01")
            .selectHobbySports()
            .selectHobbyMusic()
            .uploadPicture("C:/Users/User/Downloads/test.jpg")
            .setCurrentAddress("г. Москва, ул. Пушкина, д. 1")
            .setState("Москва")
            .setCity("Москва")
            .submit();
        java.util.Map<String, String> submitted = page.getSubmittedData();
        Assertions.assertEquals("Иван Петров", submitted.get("Имя"));
        Assertions.assertEquals("ivan.petrov@example.com", submitted.get("Email"));
        Assertions.assertEquals("Мужской", submitted.get("Пол"));
        Assertions.assertEquals("9123456789", submitted.get("Телефон"));
        Assertions.assertEquals("2023-01-01", submitted.get("Дата рождения"));
        Assertions.assertEquals("Спорт, Музыка", submitted.get("Хобби"));
        Assertions.assertEquals("test.jpg", submitted.get("Фотография"));
        Assertions.assertEquals("г. Москва, ул. Пушкина, д. 1", submitted.get("Текущий адрес"));
        Assertions.assertEquals("Москва", submitted.get("Область"));
        Assertions.assertEquals("Москва", submitted.get("Город"));
        try { Thread.sleep(10000); } catch (InterruptedException ignored) {}
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
    }