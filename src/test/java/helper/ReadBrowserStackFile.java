package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadBrowserStackFile {

    Properties properties = new Properties();

    public ReadBrowserStackFile(){
        try {
            FileInputStream fileInputStream = new FileInputStream("browserStack.properties");;
            try {
                properties.load(fileInputStream);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getServer(){
        String serverURL= properties.getProperty("appium.serverURL");
        if (serverURL!=null)
            return serverURL;
        else
            throw new RuntimeException("Error al ingresar el servidor en el archivo properties");
    }

    public String getUser(){
        String user= properties.getProperty("browserstack.user");
        if (user!=null)
            return user;
        else
            throw new RuntimeException("Error al ingresar el user en el archivo properties");
    }

    public String getKey(){
        String Key= properties.getProperty("browserstack.key");
        if (Key!=null)
            return Key;
        else
            throw new RuntimeException("Error al ingresar el key en el archivo properties");
    }
}
