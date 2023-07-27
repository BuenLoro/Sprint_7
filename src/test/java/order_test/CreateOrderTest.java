package order_test;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.scooter.order.Order;
import ru.yandex.praktikum.scooter.order.OrderSteps;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

    @RunWith(Parameterized.class)
public class CreateOrderTest {
    private final List<String> color;
    private int getTrack;
    private OrderSteps orderSteps;
    public CreateOrderTest(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters
    public static Object[][] chooseColorOfScooter() {
        return new Object[][] {
                 {List.of("BLACK")},
                 {List.of("GRAY")},
                 {List.of("BLACK, GRAY")},
                 {List.of()}
        };
    }
    @Before
    public void setUp() {
        orderSteps = new OrderSteps();
    }
    @Test
    @DisplayName("Заказ с разными цветами самоката")
    @Description("Проверка корректности создания заказа с разными цветами самоката")
    public void createOrderWithDifferentColorOfScooter() {
        Order order = new Order(color);
        ValidatableResponse responseCreateOrder = orderSteps.createNewOrder(order);
        getTrack = responseCreateOrder.extract().path("track");
        responseCreateOrder.assertThat().statusCode(201)
                .body("track", CoreMatchers.is(CoreMatchers.notNullValue()));
        }

    @After
    @Step("Отмена заказа")
    public void deleteOrder() {
        orderSteps.cancelOrder(getTrack);
    }

}
