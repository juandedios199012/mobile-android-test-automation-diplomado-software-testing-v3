package runner;

import activity.Order.OrdersListScreen;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import task.*;

public class SalesForceOrderModifyStepDefinition {

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

    @Given("el vendedor tiene un pedido")
    public void elVendedorTieneUnPedido() throws InterruptedException {
        login.withTheData("uniflexqa", "vendedor", "123");
        synchronization.synchronizaData();
        searchCustomer.selectCustomer();
        showMenuMobile.selectOrderMenu();
        gotoOrder.goToOrderToCreate();
        addOrder.withTheData("","");
    }

    @When("ingresa al pedido")
    public void ingresaAlPedido() throws InterruptedException {
        gotoOrder.goToOrderToModify();
    }

    @Then("graba la modificacion")
    public void grabaLaModificacion() throws InterruptedException {
        modifyOrder.withTheData();
        goToPurchaseSummary.goToPurchaseSummary();
        saveOrder.onClickSaveOrder();

        Thread.sleep(6000);

        ordersListScreen.pedidoMensajeLabel.isVisibility();

        Assert.assertEquals("OK", ordersListScreen.pedidoMensajeLabel.getText());
        Assert.assertEquals("Modificar", ordersListScreen.operacionLabel.getText());
    }
}
