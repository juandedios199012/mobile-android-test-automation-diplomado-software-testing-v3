@CrudOrder
Feature: Order

  @SalesForceOrder
  Scenario: Como vendedor quiero crear, modificar y anular la venta de productos por portafolio
    Given El vendedor inicia sesion
    And El vendedor sincroniza la data
    When El vendedor selecciona al cliente
    And ingresa los productos y sus cantidades "1" "4"
    Then graba el pedido con el mensaje "OK"

    Given El vendedor selecciona un pedido
    When El vendedor modifica el pedido
    Then graba la modificacion con el mensaje "OK"
    And y el estado de Operacion "Modificar"

    When El vendedor Anula el pedido
    Then se actualiza el estado del Pedido a Anulado

