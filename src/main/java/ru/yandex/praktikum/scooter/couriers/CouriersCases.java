package ru.yandex.praktikum.scooter.couriers;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
public class CouriersCases {
    @Step("Возможна регистрация нового курьера с валидными данными")
    public void createCourierPassed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(201)
                .body("ok", is(true));
    }
    @Step("Невозможно создать двух одинаковых курьеров (с одинаковыми данными)")
    public void createTwoIdenticalCouriersFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(409)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

   @Step("Невозможна регистрация курьера при отсутсвии заполнения одного из полей")
    public void createCourierWithoutFullInfoFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Проверка получения ID при вводе валидных данных в поле Логин")
    public int loginCourierPassed(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("id", greaterThan(0))
                .extract()
                .path("id");
    }
    @Step("Невозможен вход в аккаунт при отсутсвии заполнения одного из полей (логин или пароль")
    public void loginCourierWithoutFullInfoFailed(ValidatableResponse response) {
        response.assertThat()
                .statusCode(400)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
    @Step("Ошибка при получении ID при вводе невалидных данных в поле Логин или Пароль")
    public void loginCourierErrorAccountNotFound(ValidatableResponse response) {
        response.assertThat()
                .statusCode(404)
                .body("message", equalTo("Учетная запись не найдена"));
    }
}
