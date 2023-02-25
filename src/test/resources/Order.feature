Feature: Order

  #implementar background con login

  @order
  Scenario: Como vendedor quiero realizar la venta de productos mayores a 30 soles

    Given el vendedor abre el aplicativo2
    When ingresa sus credenciales "uniflexqa" "vendedor" "123"
    #* ingresa al aplicativo [aceptar]
    Then graba el pedido

  @modify
  Scenario: Como vendedor quiero modificar la venta

    Given el vendedor tiene un pedido
    When ingresa al pedido
    #* ingresa al aplicativo [aceptar]
    Then graba la modificacion

  @cancel
  Scenario: Como vendedor quiero anular la venta

    Given el vendedor ingreso un pedido
    When anula el pedido
    #* ingresa al aplicativo [aceptar]
    Then se actualiza el estado del Pedido a Anulado