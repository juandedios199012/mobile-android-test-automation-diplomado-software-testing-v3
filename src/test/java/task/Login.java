package task;

import activity.login.LoginScreen;
import helper.DataProvider;
import helper.JsonTestDataHelper;
import io.cucumber.core.internal.com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    LoginScreen loginScreen = new LoginScreen();
    DataProvider dataProvider = new DataProvider();

    public void withTheData() throws JsonProcessingException {
        logger.info("Empresa ingresada");
        loginScreen.empresaTextBox.setText(dataProvider.getOrderData().getEmpresa());

        logger.info("Usuario ingresado");
        loginScreen.usuarioTextBox.setText(dataProvider.getOrderData().getUsuario());

        logger.info("Clave ingresada");
        loginScreen.passwordTextBox.setText(dataProvider.getOrderData().getClave());

        logger.info("Clic en el boton Aceptar");
        loginScreen.ingresarButton.click();
    }

}
