package runner;

import activity.login.LoginScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class LoginStepDefinition {

    LoginScreen loginScreen = new LoginScreen();

    @Given("^el vendedor abre el aplicativo$")
    public void elVendedorAbreElAplicativo() {
    }

    @When("ingresa los datos del login {string} {string} {string}")
    public void ingresaLosDatosDelLoginVendedor(String empresa, String usuario, String clave) {
        loginScreen.empresaTextBox.setText(empresa);
        loginScreen.usuarioTextBox.setText(usuario);
        loginScreen.passwordTextBox.setText(clave);
    }

    @And("ingresa al aplicativo [aceptar]")
    public void ingresaAlAplicativo() {
        loginScreen.ingresarButton.click();
    }

    @Then("se muestra el menu de {string}")
    public void seMuestraElMenuDeClientes(String expectedResult) {
        Assertions.assertEquals(expectedResult, loginScreen.clienteLabel.getText(),
                "ERROR Vuelve al colegio");
    }
}
