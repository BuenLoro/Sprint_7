package order_test;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import ru.yandex.praktikum.scooter.order.OrderSteps;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderListTest {
    @Test
    @DisplayName("Получить список заказов")
    @Description("Проверка успешного получения списка заказов")
    public void getOrderList() {
        OrderSteps orderSteps = new OrderSteps();
        ValidatableResponse responseGetOrderList = orderSteps.getOrderList();
        responseGetOrderList.assertThat().statusCode(200).body("orders", notNullValue());
    }
}
