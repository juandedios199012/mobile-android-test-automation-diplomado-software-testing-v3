Feature: Sales Force Order

  @SalesForceOrder
  Scenario Outline: Como vendedor quiero crear, modificar y anular la venta de productos
    Given El vendedor inicia sesion
    And El vendedor sincroniza la data
    When El vendedor selecciona al cliente "<cliente>" "<tipo>"
    And ingresa los productos y sus cantidades "1" "4"
    Then graba el pedido con el mensaje "OK"

    Examples:
      | cliente                         | tipo            |
      | ESCALANTE MARIÑO, ADELMA        | Portafolio      |
