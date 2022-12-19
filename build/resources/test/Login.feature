Feature: Login
  @movile
  Scenario: Como usuario quiero ingresar al aplicativo para realizar mis operaciones

    Given el vendedor abre el aplicativo
    When ingresa los datos del login "<empresa>" "<usuario>" "<clave>"
    * ingresa al aplicativo
    Then se muestra las opciones principales