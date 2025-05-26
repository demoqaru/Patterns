import pytest
from selenium.webdriver import Firefox
from student_registration_page import StudentRegistrationPage
from test_data import test_data
import time

@pytest.mark.parametrize("data", test_data)
def test_successful_registration(data):
    driver = Firefox()
    page = StudentRegistrationPage(driver)
    try:
        (page.open()
            .set_first_name(data["first_name"])
            .set_last_name(data["last_name"])
            .set_email(data["email"])
            .select_gender(data["gender"])
            .set_mobile(data["mobile"])
            .set_date_of_birth(data["date_of_birth"])
        )
        for hobby in data["hobbies"]:
            page.select_hobby(hobby)
        (page.upload_picture(data["picture"])
            .set_current_address(data["address"])
            .set_state(data["state"])
            .set_city(data["city"])
            .submit())
        submitted = page.get_submitted_data()
        for key, value in data["expected"].items():
            assert submitted[key] == value
        time.sleep(3)
    finally:
        driver.quit()

if __name__ == "__main__":
    pytest.main([__file__]) 