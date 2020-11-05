package com.tsoft.bot.frontend.pageobject.Login;

import com.tsoft.bot.frontend.baseClass.web.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class HomePrincipalPageObject extends BaseClass {

    public  WebDriver driver;
    static GenerateWord generateWord = new GenerateWord();

    private static final String EXCEL_WEB = "excel/DATA_CLIENTE_WAPPE_MOVISTAR.xlsx";
    private static final String EXCEL_SHEET = "RRAA";
    private static final String COLUMNA_NUM_DOCUMENTO = "NumDocumento";
    private static final String COLUMNA_EMPRESA = "Empresa";
    private static final String COLUMNA_ROLES = "Roles";


    //Menu principal
    public  final String MENU_MIS_PRODUCTOS ="//a[contains(text(),'Mis Productos')]";
    public static String BTN_EMPRESA_MAQUINARIA = "//app-select/main/section[1]/article[1]/figure/div[2]/small";
    public HomePrincipalPageObject() {
        this.driver = Hook.getDriver();
    }

    private static List<HashMap<String, String>> getData2() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, EXCEL_SHEET);
    }

    public  void seleccionarMenuMisProductos() throws Throwable {
        try {
            click(driver, "xpath", BTN_EMPRESA_MAQUINARIA);
            click(driver, "xpath", MENU_MIS_PRODUCTOS);
            ExtentReportUtil.INSTANCE.stepPass(driver, "Se selecciono Opcion menu Mis Productos");
            generateWord.sendText("Se selecciono Opcion menu Mis Productos");
            generateWord.addImageToWord(driver);
        } catch (Exception e) {
            //ExcelReader.writeCellValue(EXCEL_WEB, EXCEL_SHEET, 1, 6, "FAIL");
            ExtentReportUtil.INSTANCE.stepFail(driver, "Fallo el caso de prueba : " + e.getMessage());
            generateWord.sendText("Tiempo de espera ha excedido");
            generateWord.addImageToWord(driver);
            throw e;
        }

    }





}
