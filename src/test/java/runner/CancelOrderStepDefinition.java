package runner;

import activity.Order.OrdersListScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import task.*;

public class CancelOrderStepDefinition {

    AddOrder addOrder = new AddOrder();
    Login login = new Login();
    OrdersListScreen ordersListScreen = new OrdersListScreen();
    ModifyOrder modifyOrder = new ModifyOrder();
    GotoOrder gotoOrder = new GotoOrder();
    GoToPurchaseSummary goToPurchaseSummary = new GoToPurchaseSummary();
    SaveOrder saveOrder = new SaveOrder();
    Synchronization synchronization = new Synchronization();
    ShowMenuMobile showMenuMobile = new ShowMenuMobile();
    SearchCustomer searchCustomer = new SearchCustomer();


    @Given("el vendedor ingreso un pedido")
    public void elVendedorIngresoUnPedido() throws InterruptedException {
        login.withTheData("uniflexqa", "vendedor", "123");
        synchronization.synchronizaData();
        searchCustomer.selectCustomer();
        showMenuMobile.selectOrderMenu();
        gotoOrder.goToOrderToCreate();
        addOrder.withTheData("","");
    }

    @When("anula el pedido")
    public void anulaElPedido() {
        ordersListScreen.pedidoMensajeLabel.isVisibility();
        ordersListScreen.pedidoMensajeLabel.click();

        ordersListScreen.anularButton.isVisibility();
        ordersListScreen.anularButton.click();

        ordersListScreen.confirmacionAnulacionButton.click();
    }

    @Then("se actualiza el estado del Pedido a Anulado")
    public void seActualizaElEstadoDePedidoAAnulado() {
        //Thread.sleep(6000);
        ordersListScreen.pedidoMensajeLabel.isControlDisplayed();
        Assert.assertEquals("ANULADO", ordersListScreen.pedidoMensajeLabel.getText());
        Assert.assertEquals("Anular", ordersListScreen.operacionLabel.getText());

    }
}
