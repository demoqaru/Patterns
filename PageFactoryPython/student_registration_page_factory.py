from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.remote.webelement import WebElement
from selenium.webdriver import Firefox
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.common.by import By
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.common.keys import Keys
import time

class StudentRegistrationPage:
    URL = "http://demoqa.ru/qa-auto/forms"
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)
    first_name = (By.ID, "firstName")
    last_name = (By.ID, "lastName")
    email = (By.ID, "email")
    gender_male = (By.ID, "gender-male")
    mobile = (By.ID, "mobile")
    date_of_birth_btn = (By.XPATH, "//button[contains(@class,'justify-start')]")
    hobby_sports = (By.ID, "hobby-sports")
    hobby_music = (By.ID, "hobby-music")
    picture_upload = (By.ID, "picture-upload")
    current_address = (By.ID, "currentAddress")
    state = (By.ID, "state")
    city = (By.ID, "city")
    submit_btn = (By.XPATH, "//button[@type='submit']")

    def open(self):
        self.driver.get(self.URL)
        return self
    def set_first_name(self, value):
        self.driver.find_element(*self.first_name).send_keys(value)
        return self
    def set_last_name(self, value):
        self.driver.find_element(*self.last_name).send_keys(value)
        return self
    def set_email(self, value):
        self.driver.find_element(*self.email).send_keys(value)
        return self
    def select_gender(self):
        self.driver.find_element(*self.gender_male).click()
        return self
    def set_mobile(self, value):
        self.driver.find_element(*self.mobile).send_keys(value)
        return self
    
    time.sleep(0.5)
    def set_date_of_birth(self, value):
        from datetime import datetime
        self.driver.find_element(By.XPATH, "//button[contains(@class,'justify-start')]").click()
        self.wait.until(
            EC.visibility_of_element_located((By.CLASS_NAME, "rdp"))
        )
        date_obj = datetime.strptime(value, "%Y-%m-%d")
        target_year = date_obj.year
        target_month = date_obj.month
        target_day = date_obj.day

        months = [
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ]

        while True:
            caption = self.driver.find_element(By.CSS_SELECTOR, "div.text-sm.font-medium").text.strip()
            # caption = "May 2025"
            if not caption:
                raise Exception("Не удалось найти заголовок месяца/года в календаре")
            current_month_name, current_year = caption.split()
            current_month = months.index(current_month_name) + 1
            current_year = int(current_year)
            if current_year > target_year or (current_year == target_year and current_month > target_month):
                self.driver.find_element(By.CSS_SELECTOR, "button[name='previous-month']").click()
            elif current_year < target_year or (current_year == target_year and current_month < target_month):
                self.driver.find_element(By.CSS_SELECTOR, "button[name='next-month']").click()
            else:
                break

        # Кликаем по дню (только из текущего месяца)
        day_button = self.driver.find_element(By.XPATH, f"//button[not(contains(@class,'day-outside')) and text()='{target_day}']")
        day_button.click()
        return self

    def select_hobby_sports(self):
        self.driver.find_element(*self.hobby_sports).click()
        return self
    def select_hobby_music(self):
        self.driver.find_element(*self.hobby_music).click()
        return self
    def upload_picture(self, file_path):
        self.driver.find_element(*self.picture_upload).send_keys(file_path)
        return self
    def set_current_address(self, value):
        self.driver.find_element(*self.current_address).send_keys(value)
        return self
    def set_state(self, value):
        self.driver.find_element(*self.state).send_keys(value)
        return self
    def set_city(self, value):
        self.driver.find_element(*self.city).send_keys(value)
        return self
    def submit(self):
        self.driver.find_element(*self.submit_btn).click()
        return self

    def get_submitted_data(self):
        """
        Возвращает словарь с отправленными данными из блока "Отправленная информация"
        """
        # Ждем появления блока с результатами
        self.wait.until(
            EC.visibility_of_element_located((By.XPATH, "//h2[contains(text(),'Отправленная информация')]"))
        )
        # Получаем все <p> внутри блока с результатами
        # Сначала ищем <h2>, потом его родительский <div>, а в нем все <p>
        h2_elem = self.driver.find_element(By.XPATH, "//h2[contains(text(),'Отправленная информация')]")
        result_block = h2_elem.find_element(By.XPATH, "./following-sibling::div")
        rows = result_block.find_elements(By.TAG_NAME, "p")
        data = {}
        for row in rows:
            text = row.text
            if ":" in text:
                key, value = text.split(":", 1)
                data[key.strip()] = value.strip()
        return data
