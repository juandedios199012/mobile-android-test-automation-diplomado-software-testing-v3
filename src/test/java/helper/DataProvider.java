package helper;

import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;


public class DataProvider {

    ObjectMapper objectMapper = new ObjectMapper();

    public void orderDataProvider() {
        try {
            Order order = objectMapper.readValue("resources/json/order.json", Order.class);
        } catch (Exception e) {
        }

    }
}
