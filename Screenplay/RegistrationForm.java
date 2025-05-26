package Screenplay;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationForm {
    public static final Target DATE_OF_BIRTH_BTN = Target.the("Date of Birth Button")
            .located(By.xpath("//button[contains(@class,'justify-start')]"));
    public static final Target CALENDAR = Target.the("Calendar")
            .located(By.className("rdp"));
    public static final Target CALENDAR_CAPTION = Target.the("Calendar caption")
            .located(By.cssSelector("div.text-sm.font-medium"));
    public static final Target PREV_MONTH_BTN = Target.the("Previous month button")
            .located(By.cssSelector("button[name='previous-month']"));
    public static final Target NEXT_MONTH_BTN = Target.the("Next month button")
            .located(By.cssSelector("button[name='next-month']"));
    public static final Target FIRST_NAME = Target.the("First Name").located(By.id("firstName"));
    public static final Target LAST_NAME = Target.the("Last Name").located(By.id("lastName"));
    public static final Target EMAIL = Target.the("Email").located(By.id("email"));
    public static final Target GENDER_MALE = Target.the("Gender Male").located(By.id("gender-male"));
    public static final Target MOBILE = Target.the("Mobile").located(By.id("mobile"));
    public static final Target HOBBY_SPORTS = Target.the("Hobby Sports").located(By.id("hobby-sports"));
    public static final Target HOBBY_MUSIC = Target.the("Hobby Music").located(By.id("hobby-music"));
    public static final Target PICTURE_UPLOAD = Target.the("Picture Upload").located(By.id("picture-upload"));
    public static final Target CURRENT_ADDRESS = Target.the("Current Address").located(By.id("currentAddress"));
    public static final Target STATE = Target.the("State").located(By.id("state"));
    public static final Target CITY = Target.the("City").located(By.id("city"));
    public static final Target SUBMIT = Target.the("Submit").located(By.xpath("//button[@type='submit']"));
} 