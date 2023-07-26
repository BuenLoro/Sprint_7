package ru.yandex.praktikum.scooter.couriers;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.scooter.ApiPath.COURIER_LOGIN;
import static ru.yandex.praktikum.scooter.ApiPath.NEW_COURIER;


public class CourierSteps {
    public static RequestSpecification getRequest() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://qa-scooter.praktikum-services.ru");
    }
    @Step("Создание курьера")
    public ValidatableResponse courierCreate(Courier courier){
        return getRequest()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(NEW_COURIER)
                .then();
    }

    @Step("Создание дублирующего курьера")
    public ValidatableResponse createCourierDuplicate(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(NEW_COURIER)
                .then();
    }

    @Step("Создание курьера с неполными данными (без логина или пароля)")
    public ValidatableResponse createCourierWithoutFullInfoFailed(Courier courier){
        return getRequest()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(NEW_COURIER)
                .then();
    }

    @Step("Логин курьера")
    public ValidatableResponse courierLogin(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }

    @Step("Создание курьера при вводе данных в поле Логин или Пароль с опечаткой")
    public ValidatableResponse accountNotFound(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }
    @Step("Получение ID для удаления")
    public ValidatableResponse courierGetId(Courier courier){
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(COURIER_LOGIN)
                .then();
    }

    @Step("Удаление курьера")
    public void courierDelete(int id){
        given()
                .delete(NEW_COURIER + id)
                .then();
    }
}
