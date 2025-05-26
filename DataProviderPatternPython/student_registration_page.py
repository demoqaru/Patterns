from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

class StudentRegistrationPage:
    URL = "http://localhost:5173/qa-auto/forms"
    def __init__(self, driver):
        self.driver = driver
        self.wait = WebDriverWait(driver, 10)
    def open(self):
        self.driver.get(self.URL)
        return self
    def set_first_name(self, value):
        self.driver.find_element(By.ID, "firstName").send_keys(value)
        return self
    def set_last_name(self, value):
        self.driver.find_element(By.ID, "lastName").send_keys(value)
        return self
    def set_email(self, value):
        self.driver.find_element(By.ID, "email").send_keys(value)
        return self
    def select_gender(self, gender: str):
        self.driver.find_element(By.ID, f"gender-{gender}").click()
        return self
    def set_mobile(self, value):
        self.driver.find_element(By.ID, "mobile").send_keys(value)
        return self
    def set_date_of_birth(self, value):
        from datetime import datetime
        time.sleep(0.5)
        self.driver.find_element(By.XPATH, "//button[contains(@class,'justify-start')]").click()
        self.wait.until(EC.visibility_of_element_located((By.CLASS_NAME, "rdp")))
        date_obj = datetime.strptime(value, "%Y-%m-%d")
        target_year = date_obj.year
        target_month = date_obj.month
        target_day = date_obj.day
        months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
        while True:
            caption = self.driver.find_element(By.CSS_SELECTOR, "div.text-sm.font-medium").text.strip()
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
        day_button = self.driver.find_element(By.XPATH, f"//button[not(contains(@class,'day-outside')) and text()='{target_day}']")
        day_button.click()
        return self
    def select_hobby(self, hobby: str):
        self.driver.find_element(By.ID, f"hobby-{hobby}").click()
        return self
    def upload_picture(self, file_path):
        self.driver.find_element(By.ID, "picture-upload").send_keys(file_path)
        return self
    def set_current_address(self, value):
        self.driver.find_element(By.ID, "currentAddress").send_keys(value)
        return self
    def set_state(self, value):
        self.driver.find_element(By.ID, "state").send_keys(value)
        return self
    def set_city(self, value):
        self.driver.find_element(By.ID, "city").send_keys(value)
        return self
    def submit(self):
        self.driver.find_element(By.XPATH, "//button[@type='submit']").click()
        return self
    def get_submitted_data(self):
        self.wait.until(EC.visibility_of_element_located((By.XPATH, "//h2[contains(text(),'Отправленная информация')]")))
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