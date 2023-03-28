Feature: Sales Force Order

  @SalesForceOrder
  Scenario Outline: Como vendedor quiero crear, modificar y anular la venta de productos
    Given El vendedor inicia sesion
    And El vendedor sincroniza la data
    When El vendedor selecciona al cliente "<cliente>" "<tipo>"
    And ingresa los productos y sus cantidades "1" "4"
    Then graba el pedido con el mensaje "OK"

    Given El vendedor selecciona un pedido
    When El vendedor modifica el pedido
    Then graba la modificacion con el mensaje "OK"
    And y el estado de Operacion "Modificar"

    Given El vendedor Anula el pedido
    Then se actualiza el estado del Pedido a "ANULADO"
    And y el estado de Operacion de la Anulacion "Anular"
    Examples:
      | cliente                         | tipo            |
      | ESCALANTE MARIÑO, ADELMA        | Portafolio      |
      | PUCUTAY PALMA, FELICITA EDELINA | Fuerza de Venta |
