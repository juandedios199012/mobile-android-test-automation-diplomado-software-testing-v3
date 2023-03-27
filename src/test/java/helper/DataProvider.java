package helper;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;
import task.Login;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class DataProvider {

    public Order getOrderData() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("src/test/resources/json/order.json");
            Scanner myReader = new Scanner(file);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();

            return objectMapper.readValue(data, Order.class);

        } catch (JsonProcessingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Order();
    }
}
