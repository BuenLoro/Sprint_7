package couriers_test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.scooter.couriers.Courier;
import ru.yandex.praktikum.scooter.couriers.CourierSteps;
import ru.yandex.praktikum.scooter.couriers.CouriersCases;

public class CreateCourierTest {
    int courierId;
    private Courier courier;
    private CouriersCases couriersCases;
    private CourierSteps courierSteps;

    @Before
    @Step("Создание тестовых данных курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        couriersCases = new CouriersCases();
        courier = new Courier("12lo5d8j9k0", "tgintlkin5gn");
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание нового курьера")
    @Description("Проверяем, что курьера можно создать")
    public void courierCanBeCreatedTest() {
        ValidatableResponse responseCreateCourier = courierSteps.courierCreate(courier);
        courierId = courierSteps.courierGetId(courier).extract().path("id");
        couriersCases.createCourierPassed(responseCreateCourier);
    }

    @Test
    @DisplayName("Создание дублирующего курьера")
    @Description("Проверяем, что дублирующего курьера нельзя создать")
    public void createTwoIdenticalCouriersTest() {
        courier = new Courier("ninja", "1234");
        ValidatableResponse responseCreateIdenticalCouriers = courierSteps.courierCreate(courier);
        couriersCases.createTwoIdenticalCouriersFailed(responseCreateIdenticalCouriers);
    }

    @Test
    @DisplayName("Проверяем, что нельзя создать курьера, не заполнив поле Логин")
    public void createCourierWithoutLoginTest(){
        courier = new Courier("", "1234");
        ValidatableResponse response = courierSteps.createCourierWithoutFullInfoFailed(courier);
        couriersCases.createCourierWithoutFullInfoFailed(response);
    }

    @Test
    @DisplayName("Проверяем, что нельзя создать курьера, не заполнив поле Пароль")
    public void createCourierWithoutPasswordTest() {
        courier = new Courier("saske", "");
        ValidatableResponse response = courierSteps.createCourierWithoutFullInfoFailed(courier);
        couriersCases.createCourierWithoutFullInfoFailed(response);
    }

    @After
    @Step("Удаление тестовых данных")
    public void deleteCourier() {
        if (courierId != 0) {
            courierSteps.courierDelete(courierId);
            System.out.println("DELETED");
        } else {
            System.out.println("NOT DELETED");
        }
    }
}
