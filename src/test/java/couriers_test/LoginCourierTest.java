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

public class LoginCourierTest {
    private Courier courier;
    private CouriersCases couriersCases;
    private CourierSteps courierSteps;
    int courierId;

    @Before
    @Step("Создание тестовых данных курьера")
    public void setUp() {
        courierSteps = new CourierSteps();
        couriersCases = new CouriersCases();
        courier = new Courier("12lo5d8j9k0", "tgintlkin5gn");
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
        ValidatableResponse responseCreateCourier = courierSteps.courierCreate(courier);
        courierId = courierSteps.courierGetId(courier).extract().path("id");
    }

    @Test
    @DisplayName("Курьер может авторизоваться")
    @Description("Проверяем, что курьер может успешно авторизоваться")
    public void loginTest() {
        ValidatableResponse responseLoginCourier = courierSteps.courierLogin(courier);
        couriersCases.loginCourierPassed(responseLoginCourier);
    }

    @Test
    @DisplayName("Нельзя авторизоваться при неправильном вводе логина или пароля")
    @Description("Курьер не может авторизоваться при наличии опечатки в поле Логин или Пароль")
    public void loginOrPasswordWithErrorTest() {
        courier = new Courier("saske", "12inkf34");
        ValidatableResponse responseNotLoginCourier = courierSteps.accountNotFound(courier);
        couriersCases.loginCourierErrorAccountNotFound(responseNotLoginCourier);
    }

    @Test
    @DisplayName("Нельзя авторизоваться без ввода Логина или Пароля")
    @Description("Курьер не может авторизоваться без ввода Логина или Пароля")
    public void loginWithoutLoginTest() {
        courier = new Courier("saske", "");
        ValidatableResponse responseNotLoginCourier = courierSteps.courierLogin(courier);
        couriersCases.loginCourierWithoutFullInfoFailed(responseNotLoginCourier);
    }

    @Test
    @DisplayName("Нельзя авторизоваться без предварительной регистрации")
    @Description("Курьер не может авторизоваться, не зарегистрировавшись заранее")
    public void loginWithoutRegistration() {
        courier = new Courier(",gkf8i9dkjrm", "lglg0u9y");
        ValidatableResponse responseLoginCourier = courierSteps.courierLogin(courier);
        couriersCases.loginCourierErrorAccountNotFound(responseLoginCourier);
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
