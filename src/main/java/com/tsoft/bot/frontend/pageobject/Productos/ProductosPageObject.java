package com.tsoft.bot.frontend.pageobject.Productos;

import com.tsoft.bot.frontend.baseClass.web.BaseClass;
import com.tsoft.bot.frontend.helpers.Hook;
import com.tsoft.bot.frontend.utility.ExcelReader;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.List;

public class ProductosPageObject extends BaseClass {


    public static WebDriver driver;
    static GenerateWord generateWord = new GenerateWord();

    private static final String EXCEL_WEB = "excel/DATA_CLIENTE_WAPPE_MOVISTAR.xlsx";
    private static final String EXCEL_SHEET = "Productos";
    private static final String COLUMNA_COD_PRODUCTO = "Codproducto";

// selenium

    public static String CAR_PRODUCTOS_OFFICE = "//app-module-services/app-main/div/div/section[1]/section/app-tab[2]/div/div[1]";
    public static String INPUT_BUSCAR_PRODUCTO_OFFICE = "/html/body/app-root/app-module-services/app-main/div/div/section[2]/app-list-services/section[1]/div/div[1]/app-search/div/input";
    public static String INPUT_BUSCAR_PRODUCTO_MOVILES = "//app-module-services/app-main/div/div/section[2]/app-list-services/section[1]/div/div[1]/app-search/div/input";


    public ProductosPageObject() {
        this.driver = Hook.getDriver();
    }

    private static List<HashMap<String, String>> getData() throws Throwable {
        return ExcelReader.data(EXCEL_WEB, EXCEL_SHEET);
    }

    public static void buscarProcutosOfice(String casoDePrueba) throws Throwable {
        try {
            int valores = Integer.parseInt(casoDePrueba) - 1;
            String codProductoData = getData().get(valores).get(COLUMNA_COD_PRODUCTO);

           click(driver, "xpath", CAR_PRODUCTOS_OFFICE);
            sleep2(2);
            sendKeyValue(driver, "xpath", INPUT_BUSCAR_PRODUCTO_OFFICE, codProductoData);
            sleep2(3);

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


}
