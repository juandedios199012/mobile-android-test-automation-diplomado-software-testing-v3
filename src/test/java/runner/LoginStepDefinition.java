package runner;

import activity.login.LoginScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefinition {

    LoginScreen loginScreen = new LoginScreen();

    @Given("El vendedor abre el aplicativo")
    public void elVendedorAbreElAplicativo() {
        Assertions.assertTrue(loginScreen.empresaTextBox.isControlDisplayed());
    }

    @When("El vendedor ingresa sus credenciales {string} {string} {string}")
    public void elVendedorIngresaSusCredenciales(String empresa, String usuario, String clave) {
        loginScreen.empresaTextBox.setText(empresa);
        loginScreen.usuarioTextBox.setText(usuario);
        loginScreen.passwordTextBox.setText(clave);
        loginScreen.ingresarButton.click();
    }

    @Then("El vendedor inicia sesion")
    public void elVendedorIniciaSesion() {
        String expectedResult= "Clientes";
        Assertions.assertEquals(expectedResult, loginScreen.clienteLabel.getText(),
                "ERROR, no inicio sesion");
    }
}

