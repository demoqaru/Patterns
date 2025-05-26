import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://demoqa.ru/qa-auto/forms";

    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "gender-male")
    private WebElement genderMale;
    @FindBy(id = "mobile")
    private WebElement mobileInput;
    @FindBy(xpath = "//button[contains(@class,'justify-start')]")
    private WebElement dateOfBirthBtn;
    @FindBy(id = "hobby-sports")
    private WebElement hobbySports;
    @FindBy(id = "hobby-music")
    private WebElement hobbyMusic;
    @FindBy(id = "picture-upload")
    private WebElement pictureUpload;
    @FindBy(id = "currentAddress")
    private WebElement currentAddressInput;
    @FindBy(id = "state")
    private WebElement stateInput;
    @FindBy(id = "city")
    private WebElement cityInput;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitBtn;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }
    public RegistrationPage open() {
        driver.get(URL);
        return this;
    }
    public RegistrationPage setFirstName(String value) {
        firstNameInput.sendKeys(value);
        return this;
    }
    public RegistrationPage setLastName(String value) {
        lastNameInput.sendKeys(value);
        return this;
    }
    public RegistrationPage setEmail(String value) {
        emailInput.sendKeys(value);
        return this;
    }
    public RegistrationPage selectGender() {
        genderMale.click();
        return this;
    }
    public RegistrationPage setMobile(String value) {
        mobileInput.sendKeys(value);
        return this;
    }
    public RegistrationPage setDateOfBirth(String value) {
        dateOfBirthBtn.click();
        wait.until(ExpectedConditions.visibilityOf_element_located(By.className("rdp")));
        String[] parts = value.split("-");
        int targetYear = Integer.parseInt(parts[0]);
        int targetMonth = Integer.parseInt(parts[1]);
        int targetDay = Integer.parseInt(parts[2]);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        while (true) {
            String caption = driver.findElement(By.cssSelector("div.text-sm.font-medium")).getText().trim();
            if (caption.isEmpty()) throw new RuntimeException("Не удалось найти заголовок месяца/года в календаре");
            String[] cap = caption.split(" ");
            int currentMonth = java.util.Arrays.asList(months).indexOf(cap[0]) + 1;
            int currentYear = Integer.parseInt(cap[1]);
            if (currentYear > targetYear || (currentYear == targetYear && currentMonth > targetMonth)) {
                driver.findElement(By.cssSelector("button[name='previous-month']")).click();
            } else if (currentYear < targetYear || (currentYear == targetYear && currentMonth < targetMonth)) {
                driver.findElement(By.cssSelector("button[name='next-month']")).click();
            } else {
                break;
            }
        }
        driver.findElement(By.xpath("//button[not(contains(@class,'day-outside')) and text()='" + targetDay + "']")).click();
        return this;
    }
    public RegistrationPage selectHobbySports() {
        hobbySports.click();
        return this;
    }
    public RegistrationPage selectHobbyMusic() {
        hobbyMusic.click();
        return this;
    }
    public RegistrationPage uploadPicture(String filePath) {
        pictureUpload.sendKeys(filePath);
        return this;
    }
    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.sendKeys(value);
        return this;
    }
    public RegistrationPage setState(String value) {
        stateInput.sendKeys(value);
        return this;
    }
    public RegistrationPage setCity(String value) {
        cityInput.sendKeys(value);
        return this;
    }
    public RegistrationPage submit() {
        submitBtn.click();
        return this;
    }
    public java.util.Map<String, String> getSubmittedData() {
        wait.until(ExpectedConditions.visibilityOf_element_located(By.xpath("//h2[contains(text(),'Отправленная информация')]")));
        WebElement h2Elem = driver.findElement(By.xpath("//h2[contains(text(),'Отправленная информация')]"));
        WebElement resultBlock = h2Elem.findElement(By.xpath("./following-sibling::div"));
        java.util.List<WebElement> rows = resultBlock.findElements(By.tagName("p"));
        java.util.Map<String, String> data = new java.util.HashMap<>();
        for (WebElement row : rows) {
            String text = row.getText();
            if (text.contains(":")) {
                String[] kv = text.split(":", 2);
                data.put(kv[0].trim(), kv[1].trim());
            }
        }
        return data;
    }
}