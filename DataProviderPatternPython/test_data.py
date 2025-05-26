test_data = [
    {
        "first_name": "Иван",
        "last_name": "Петров",
        "email": "ivan.petrov@example.com",
        "gender": "male",
        "mobile": "9123456789",
        "date_of_birth": "2023-01-01",
        "hobbies": ["sports", "music"],
        "picture": r"C:\Users\user\Downloads\test.jpg",
        "address": "г. Москва, ул. Пушкина, д. 1",
        "state": "Москва",
        "city": "Москва",
        "expected": {
            "Имя": "Иван Петров",
            "Email": "ivan.petrov@example.com",
            "Пол": "Мужской",
            "Телефон": "9123456789",
            "Дата рождения": "2023-01-01",
            "Хобби": "Спорт, Музыка",
            "Фотография": "test.jpg",
            "Текущий адрес": "г. Москва, ул. Пушкина, д. 1",
            "Область": "Москва",
            "Город": "Москва"
        }
    },
    # Можно добавить другие кейсы
] 