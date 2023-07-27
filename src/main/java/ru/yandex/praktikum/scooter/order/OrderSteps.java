package ru.yandex.praktikum.scooter.order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.yandex.praktikum.scooter.constants.ApiPath.*;

public class OrderSteps {
    public static RequestSpecification requestSpec() {
        return given().log().all()
                .contentType(ContentType.JSON)
                .baseUri("https://qa-scooter.praktikum-services.ru");
    }

    @Step("Создание заказа")
    public ValidatableResponse createNewOrder(Order order) {
        return requestSpec()
                .body(order)
                .when()
                .post(CREATE_ORDER)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse getOrderList() {
        return requestSpec()
                .when()
                .get(GET_ORDERS)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(int track) {
        return requestSpec()
                .body(track)
                .when()
                .put(CANCEL_ORDER)
                .then();
    }
}
