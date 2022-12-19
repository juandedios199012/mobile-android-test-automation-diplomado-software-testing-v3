package stepDefinition;

import activity.login.LoginScreen;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

    LoginScreen loginScreen = new LoginScreen();

    @Given("^el vendedor abre el aplicativo$")
    public void elVendedorAbreElAplicativo() {
    }

    @When("ingresa los datos del login {string} {string} {string}")
    public void ingresaLosDatosDelLogin(String empresa, String usuario, String clave) {
        loginScreen.empresaTextBox.setText(empresa);
        loginScreen.empresaTextBox.setText(usuario);
        loginScreen.empresaTextBox.setText(clave);
    }

    @And("ingresa al aplicativo")
    public void ingresaAlAplicativo() {
    }

    @Then("se muestra las opciones principales")
    public void seMuestraLasOpcionesPrincipales() {
    }
}
