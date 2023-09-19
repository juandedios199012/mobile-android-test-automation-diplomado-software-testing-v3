package activity.login;

import appiumControl.Button;
import appiumControl.TextBox;
import org.openqa.selenium.By;

public class LoginScreen {
    public TextBox empresaTextBox = new TextBox(By.id("aaaaacom.uniflex.flexbusinessandroid:id/eteEmpresa"));
    public TextBox usuarioTextBox = new TextBox(By.id("com.uniflex.flexbusinessandroid:id/loginEteUsuario"));
    public TextBox passwordTextBox = new TextBox(By.id("com.uniflex.flexbusinessandroid:id/LoginEtePassword"));
    public Button ingresarButton = new Button(By.id("com.uniflex.flexbusinessandroid:id/loginButIngresar"));
}
