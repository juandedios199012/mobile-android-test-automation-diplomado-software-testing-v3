Feature: Login

  @movile
  Scenario: Como vendedor quiero ingresar al aplicativo para realizar mis operaciones

    Given el vendedor abre el aplicativo
    When ingresa los datos del login "uniflexqa" "vendedor" "123"
    * ingresa al aplicativo [aceptar]
    Then se muestra el menu de "Clientes"

