package com.tsoft.bot.frontend.steps.Movistar;

import com.tsoft.bot.frontend.pageobject.Login.LoginPageObject;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends LoginPageObject {
    /*------------------------------------------------------------------------------------------------------*/
    @Given("^Usuario se encuentra en la Web Wappe Agente \"([^\"]*)\"$")
    public void usuarioSeEncuentraEnLaWebWappeAgente(String casoPrueba) throws Throwable {
        LoginPageObject.ingresoALaUrlDeWappeAgente(casoPrueba);
        //LoginPageObject.cargarWeb();
    }

    /*------------------------------------------------------------------------------------------------------*/
    @When("^Completa datos de login y da click en el boton ingresar \"([^\"]*)\"$")
    public void completaDatosDeLoginYDaClickEnElBotonIngresar(String casoPrueba) throws Throwable {
        LoginPageObject.ingresoDatosLogin(casoPrueba);
    }

    /*------------------------------------------------------------------------------------------------------*/
    @When("^Usuario ingresa numero de documento y contraseña \"([^\"]*)\"$")
    public void usuarioIngresaNumeroDeDocumentoYContraseña(String credencialesLogin) throws Throwable {
        LoginPageObject.ingresoDatosLogin(credencialesLogin);
    }


    /*------------------------------------------------------------------------------------------------------*/
    @Then("^Usuario da clic en el boton ingresa y se verifica acceso$")
    public void usuarioDaClicEnElBotonIngresaYSeVerificaAcceso() throws Throwable {
        LoginPageObject.verificarAccesoLoginAgente();
    }
}
