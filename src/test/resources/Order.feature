Feature: Order

  @order
  Scenario: Como vendedor quiero realizar la venta de productos mayores a 30 soles

    Given el vendedor abre el aplicativo2
    When ingresa sus credenciales "uniflexqa" "vendedor" "123"
    #* ingresa al aplicativo [aceptar]
    Then graba el pedido

