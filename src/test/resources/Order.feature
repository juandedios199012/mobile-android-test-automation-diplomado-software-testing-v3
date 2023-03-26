Feature: Order

  Background: El vendedor inicia sesion
    Given El vendedor abre el aplicativo
    When El vendedor ingresa sus credenciales "uniflexqa" "vendedor" "123"
    Then El vendedor inicia sesion

  @order @Complete @Test
  Scenario: Como vendedor quiero realizar la venta de productos mayores a 30 soles
    Given El vendedor sincroniza la data
    When El vendedor selecciona al cliente
    And ingresa los productos y sus cantidades "1" "4"
    Then graba el pedido con el mensaje "OK"

  @modify
  Scenario: Como vendedor quiero modificar la venta

    Given El vendedor tiene un pedido registrado
    When El vendedor elimina un producto del pedido
    And modifica cantidad de productos
    Then graba la modificacion

  @cancel
  Scenario: Como vendedor quiero anular la venta

    Given el vendedor ingreso un pedido
    When anula el pedido
    #* ingresa al aplicativo [aceptar]
    Then se actualiza el estado del Pedido a Anulado