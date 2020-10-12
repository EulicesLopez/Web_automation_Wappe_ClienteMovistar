package com.tsoft.bot.frontend.pageobject.Login;

import com.tsoft.bot.frontend.baseClass.web.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.Driver;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

import static com.tsoft.bot.frontend.baseClass.web.CommonComponents.cargarBrowser;

public class LoginPageObject extends BaseClass {

    public static WebDriver driver;
    static GenerateWord generateWord = new GenerateWord();

    private static final String EXCEL_WEB = "excel/DATA_CLIENTE_WAPPE_MOVISTAR.xlsx";
    private static final String EXCEL_SHEET = "Login";
    private static final String COLUMNA_URL = "URL";
    private static final String COLUMNA_TIPO_DOCUMENTO = "TIPO_DOCUMENTO";
    private static final String COLUMNA_DOCUMENTO = "DOCUMENTO";
    private static final String COLUMNA_PASSWORD = "PASSWORD";
// selenium

    public static String BTN_EMPRESA_MAQUINARIA = "//app-select/main/section[1]/article[1]/figure/div[2]/small";
    public static String SELECT_TIPO_DOCUMENTO = "//div[@class='mat-select-value']";
    public static String TIPO_DOC_PASAPORTE = "//span[contains(text(),'Pasaporte')]";
    public static String TIPO_CE = "//span[contains(text(),'Carné Extranjeria')]";
    public static String TIPO_OTROS = "//span[@class='mat-option-text'][contains(text(),'Otros')]";
    public static String ETIQUETA_TIPO_USUARIO = "//body/app-root/app-home/header/app-header/nav/ul/li/ul[2]/li[1]/small";
    // public static String ETIQUETA_NOMBRE_USUARIO = "//app-root/app-main/header/app-header/nav/ul/li/ul[2]/li[1]/p";
    public static String ETIQUETA_NOMBRE_USUARIO = "//body/app-root/app-home/header/app-header/nav/ul/li/ul[2]/li[1]/p";

    public static String INPUT_DOC = "mat-input-0";
    public static String INPUT_PASS = "mat-input-1";
    public static String BTN_ACEPTAR = "//button[@class='boton button-green w-100 button-responsive-login']";


    public LoginPageObject() {
        this.driver = Hook.getDriver();
    }

    private static List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, EXCEL_SHEET);
    }

    public static void ingresoALaUrlDeWappeAgente(String casoDePrueba) throws Throwable {
        try {
            int valores = Integer.parseInt(casoDePrueba) - 1;
            String url = getData().get(valores).get(COLUMNA_URL);
            driver.get(url);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se inició correctamente la página del Agente");
            generateWord.sendText("Se inició correctamente la página del Agente");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            //ExcelReader.writeCellValue(EXCEL_WEB, EXCEL_SHEET, 1, 5, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }
    }


    public static void ingresoDatosLogin(String casoDePrueba) throws Throwable {

        try {
            int valores = Integer.parseInt(casoDePrueba) - 1;
            String documentoData = getData().get(valores).get(COLUMNA_DOCUMENTO);
            String passwordData = getData().get(valores).get(COLUMNA_PASSWORD);
            selecionarTipoDocumento(casoDePrueba);

            sendKeyValue(driver, "id", INPUT_DOC, documentoData);
            sendKeyValue(driver, "id", INPUT_PASS, passwordData);
            click(driver, "xpath", BTN_ACEPTAR);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se Ingresa los datos de login: Documento: " + documentoData + " Contraseña: " + passwordData);
            generateWord.sendText("Se Ingresa los datos de login: Documento: " + documentoData + " Contraseña: " + passwordData);
            generateWord.addImageToWord(driver);
            sleep2(7);
        } catch (Exception e) {
            //ExcelReader.writeCellValue(EXCEL_WEB, EXCEL_SHEET, 1, 5, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }

    }

    /*------------------------------------------------------------------------------------------------------*/
    public static void selecionarTipoDocumento(String casoPrueba) throws Throwable {
        try {

            int valores = Integer.parseInt(casoPrueba) - 1;
            String tipoDocuemntoData = getData().get(valores).get(COLUMNA_TIPO_DOCUMENTO);

            switch (tipoDocuemntoData) {
                case "DNI":
                    break;
                case "Pasaporte":
                    click(driver, "xpath", SELECT_TIPO_DOCUMENTO);
                    click(driver, "xpath", TIPO_DOC_PASAPORTE);
                    break;
                case "CE":
                    click(driver, "xpath", SELECT_TIPO_DOCUMENTO);
                    click(driver, "xpath", TIPO_CE);
                    break;
                case "Otros":
                    click(driver, "xpath", SELECT_TIPO_DOCUMENTO);
                    click(driver, "xpath", TIPO_OTROS);
                    break;
                default:
                    ExtentReportUtil.INSTANCE.stepPass(driver, "Error: tipo de documento no valido(Excel)");
                    generateWord.sendText("Error: tipo de documento no valido(Excel)");
                    break;
            }


            ExtentReportUtil.INSTANCE.stepPass(driver, "Se seleccion el tipo de documento: " + tipoDocuemntoData);
            generateWord.sendText("Se seleccion el tipo de documento: " + tipoDocuemntoData);
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }
    }


    public static void verificarAccesoLoginAgente() throws Throwable {
        try {

            sleep2(2);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se muestra las empresas en la que esta asociado el usuario");
            generateWord.sendText("Se muestra las empresas en la que esta asociado el usuario");
            generateWord.addImageToWord(driver);

            click(driver, "xpath", BTN_EMPRESA_MAQUINARIA);

            String etiquetaTipoUsuario = driver.findElement(By.xpath(ETIQUETA_TIPO_USUARIO)).getText();
            String etiquetaNombreUsuario = driver.findElement(By.xpath(ETIQUETA_NOMBRE_USUARIO)).getText();

            ExtentReportUtil.INSTANCE.stepPass(driver, "Se verifica acceso Correcto Login Cliente: " + etiquetaNombreUsuario + " - Empresa/Rol Online: " + etiquetaTipoUsuario);
            generateWord.sendText("Se verifica acceso Correcto Login Cliente: " + etiquetaNombreUsuario + " - Empresa/Rol Online: " + etiquetaTipoUsuario);
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }

    }


    /*------------------------------------------------------------------------------------------------------*/
    public static void cargarWeb() throws Throwable {
        try {
            cargarBrowser(driver, "https://wappe.movistar.com.pe/#/agente");
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se ingresó a la pagina web Agente Wappe");
            generateWord.sendText("Se ingresó a la pagina web Agente Wappe");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }
    }

    /*------------------------------------------------------------------------------------------------------*/
}
