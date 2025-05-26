import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String URL = "https://demoqa.ru/qa-auto/forms";
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }
    public RegistrationPage open() {
        driver.get(URL);
        return this;
    }
    public RegistrationPage setFirstName(String value) {
        driver.findElement(By.id("firstName")).sendKeys(value);
        return this;
    }
    public RegistrationPage setLastName(String value) {
        driver.findElement(By.id("lastName")).sendKeys(value);
        return this;
    }
    public RegistrationPage setEmail(String value) {
        driver.findElement(By.id("email")).sendKeys(value);
        return this;
    }
    public RegistrationPage selectGender() {
        driver.findElement(By.id("gender-male")).click();
        return this;
    }
    public RegistrationPage setMobile(String value) {
        driver.findElement(By.id("mobile")).sendKeys(value);
        return this;
    }
    public RegistrationPage setDateOfBirth(String value) {
        driver.findElement(By.xpath("//button[contains(@class,'justify-start')]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("rdp")));
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
        driver.findElement(By.id("hobby-sports")).click();
        return this;
    }
    public RegistrationPage selectHobbyMusic() {
        driver.findElement(By.id("hobby-music")).click();
        return this;
    }
    public RegistrationPage uploadPicture(String filePath) {
        driver.findElement(By.id("picture-upload")).sendKeys(filePath);
        return this;
    }
    public RegistrationPage setCurrentAddress(String value) {
        driver.findElement(By.id("currentAddress")).sendKeys(value);
        return this;
    }
    public RegistrationPage setState(String value) {
        driver.findElement(By.id("state")).sendKeys(value);
        return this;
    }
    public RegistrationPage setCity(String value) {
        driver.findElement(By.id("city")).sendKeys(value);
        return this;
    }
    public RegistrationPage submit() {
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        return this;
    }
    public java.util.Map<String, String> getSubmittedData() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Отправленная информация')]")));
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
