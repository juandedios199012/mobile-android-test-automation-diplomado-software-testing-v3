package helper;

import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import model.Login;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataProvider {

    public Login getOrderData() {//throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            File file = new File("src/test/resources/json/login.json");
            Scanner myReader = new Scanner(file);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();

            return objectMapper.readValue(data, Login.class);

        } catch (JsonProcessingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return new Login();
    }
}
