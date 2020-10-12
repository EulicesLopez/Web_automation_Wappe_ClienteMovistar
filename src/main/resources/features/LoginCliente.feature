Feature: Flujos de Login Agente
  _Feature para automatizar flujo Login

  @Login-Cliente
  Scenario Outline: Login Cliente Wappe
    Given Usuario se encuentra en la Web Wappe Agente "<login>"
    When Usuario ingresa numero de documento y contraseña "<login>"
    Then Usuario da clic en el boton ingresa y se verifica acceso

    Examples:
      | login |
      | 1     |