package Screenplay;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SelectDate implements Task {
    private final String date; // формат "2023-01-01"

    public SelectDate(String date) {
        this.date = date;
    }

    public static SelectDate of(String date) {
        return new SelectDate(date);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Click.on(RegistrationForm.DATE_OF_BIRTH_BTN));
        actor.attemptsTo(WaitUntil.the(RegistrationForm.CALENDAR, isVisible()).forNoMoreThan(5).seconds());

        String[] parts = date.split("-");
        int targetYear = Integer.parseInt(parts[0]);
        int targetMonth = Integer.parseInt(parts[1]);
        int targetDay = Integer.parseInt(parts[2]);
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        while (true) {
            String caption = RegistrationForm.CALENDAR_CAPTION.resolveFor(actor).getText().trim();
            String[] cap = caption.split(" ");
            int currentMonth = java.util.Arrays.asList(months).indexOf(cap[0]) + 1;
            int currentYear = Integer.parseInt(cap[1]);

            if (currentYear > targetYear || (currentYear == targetYear && currentMonth > targetMonth)) {
                actor.attemptsTo(Click.on(RegistrationForm.PREV_MONTH_BTN));
            } else if (currentYear < targetYear || (currentYear == targetYear && currentMonth < targetMonth)) {
                actor.attemptsTo(Click.on(RegistrationForm.NEXT_MONTH_BTN));
            } else {
                break;
            }
        }

        Target dayButton = Target.the("Day Button")
                .located(By.xpath("//button[not(contains(@class,'day-outside')) and text()='" + targetDay + "']"));
        actor.attemptsTo(Click.on(dayButton));
    }
} 