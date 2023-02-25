package task;

import activity.login.LoginScreen;
import helper.JsonTestDataHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Login {

    private static final Logger logger = LogManager.getLogger(JsonTestDataHelper.class);

    LoginScreen loginScreen = new LoginScreen();

    public void withTheData(String empresa, String usuario, String clave) throws InterruptedException {
        logger.info("Empresa ingresada");
        loginScreen.empresaTextBox.setText(empresa);

        logger.info("Usuario ingresado");
        loginScreen.usuarioTextBox.setText(usuario);

        logger.info("Clave ingresada");
        loginScreen.passwordTextBox.setText(clave);

        logger.info("Clic en el boton Aceptar");
        loginScreen.ingresarButton.click();
    }

}
