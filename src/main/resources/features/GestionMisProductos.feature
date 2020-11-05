Feature: Flujos gestion mis productos
  _Feature para automatizar mis productos

  @Login-Cliente
  Scenario Outline: Gestionar Mis Productos
    Given Usuario se encuentra en la Web Wappe Agente "<login>"
    When Usuario ingresa numero de documento y contraseña "<login>"
    And Usuario da clic en menu mis productos y lista los productos
    And Ingresa codigo de producto a buscar "<caso_prueba>"
    And Usuario filtra los productos
    Then Usuario da clic en limpiar flitro y se verifica acciones ejecutadas

    Examples:
      | login |caso_prueba|
      | 1     |     1      |