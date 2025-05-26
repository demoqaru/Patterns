from selenium.webdriver import Firefox
from student_registration_page import StudentRegistrationPage
import time

def test_successful_registration():
    driver = Firefox()
    page = StudentRegistrationPage(driver)
    try:
        (page.open()
            .set_first_name("Иван")
            .set_last_name("Петров")
            .set_email("ivan.petrov@example.com")
            .select_gender("male")
            .set_mobile("9123456789")
            .set_date_of_birth("2023-01-01")
            .select_hobby("sports")
            .select_hobby("music")
            .upload_picture(r"C:/Users/User/Downloads/test.jpg")
            .set_current_address("г. Москва, ул. Пушкина, д. 1")
            .set_state("Москва")
            .set_city("Москва")
            .submit())
        submitted = page.get_submitted_data()
        assert submitted["Имя"] == "Иван Петров"
        assert submitted["Email"] == "ivan.petrov@example.com"
        assert submitted["Пол"] == "Мужской"
        assert submitted["Телефон"] == "9123456789"
        assert submitted["Дата рождения"] == "2023-01-01"
        assert submitted["Хобби"] == "Спорт, Музыка"
        assert submitted["Фотография"] == "test.jpg"
        assert submitted["Текущий адрес"] == "г. Москва, ул. Пушкина, д. 1"
        assert submitted["Область"] == "Москва"
        assert submitted["Город"] == "Москва"
        time.sleep(10)
    finally:
        driver.quit()

if __name__ == "__main__":
    test_successful_registration()
