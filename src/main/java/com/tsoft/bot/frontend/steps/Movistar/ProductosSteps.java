package com.tsoft.bot.frontend.steps.Movistar;

import com.tsoft.bot.frontend.pageobject.Login.HomePrincipalPageObject;
import com.tsoft.bot.frontend.pageobject.Productos.ProductosPageObject;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;

public class ProductosSteps extends ProductosPageObject {
    HomePrincipalPageObject homePrincipal = new HomePrincipalPageObject();

    @And("^Usuario da clic en menu mis productos y lista los productos$")
    public void usuarioDaClicEnMenuMisProductosYListaLosProductos() throws Throwable {
        homePrincipal.seleccionarMenuMisProductos();
    }


    @And("^Ingresa codigo de producto a buscar \"([^\"]*)\"$")
    public void ingresaCodigoDeProductoABuscar(String casoPrueba) throws Throwable {
        buscarProcutosOfice(casoPrueba);

    }

    @And("^Usuario filtra los productos$")
    public void usuarioFiltraLosProductos() {
    }

    @Then("^Usuario da clic en limpiar flitro y se verifica acciones ejecutadas$")
    public void usuarioDaClicEnLimpiarFlitroYSeVerificaAccionesEjecutadas() {
    }
}
