package com.tsoft.bot.frontend.baseClass.sikuli;

import com.tsoft.bot.frontend.utility.GenerateWord;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.*;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import static org.sikuli.script.Button.WHEEL_DOWN;
import static org.sikuli.script.Button.WHEEL_UP;

public class BaseClassSikuli {
    static GenerateWord generateWord = new GenerateWord();
    static WebDriver driver;
    static Screen screen = new Screen();

    public static void screenshotSikuli(String nameCapture) {
        ScreenImage imagen = screen.capture();
        System.out.println("path: " + imagen.getFile());
        try {
            FileUtils.copyFile(new File(imagen.getFile()), new File("src/main/resources/images/screenshots/" + nameCapture + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clickSikuli(String pathImage, int time) throws Throwable {
        try {
            if (screen.exists(pathImage, time) != null) {
                screen.find(pathImage).highlight("red").click();
            } else {
                generateWord.sendText("Error : No se encontró el elemento en la pagina");
            }
        } catch (Throwable t) {
            generateWord.sendText("Error : No se encontró el elemento : " + pathImage);
            generateWord.addImageToWord(driver);
            throw t;
        }
    }


    public static void doubleClick(String pathImage, int time) throws Throwable {
        try {
            if (screen.exists(pathImage, time) != null) {
                screen.find(pathImage).highlight("red").doubleClick();
            } else {
                generateWord.sendText("Error : No se encontró el elemento en la pagina");
            }
        } catch (Throwable t) {
            generateWord.sendText("Error : No se encontró el elemento : " + pathImage);
            generateWord.addImageToWord(driver);
            throw t;
        }
    }



    public static void rightClick(String pathImage, int time) throws Throwable {
        try {
            if (screen.exists(pathImage, time) != null) {
                screen.find(pathImage).rightClick();
            } else {
                generateWord.sendText("Error : No se encontró el elemento en la pagina");
            }
        } catch (Exception t) {
            generateWord.sendText("Error : No se encontró el elemento : " + pathImage);

        }
    }


    public static void type(String pathImage, String text) throws Exception {
        try {

            screen.find(pathImage).type(text);

        } catch (Exception t) {
            generateWord.sendText("Error : No se encontró el elemento : " + pathImage);

        }
    }

    public static Screen getScreen() {
        return (screen == null) ? new Screen() : screen;
    }

    public static void highlightElement(String pathImage, int time, String color) throws Throwable {
        try {
            screen.find(pathImage).highlight(time, color);
        } catch (Throwable t) {
            generateWord.sendText("Error : No se encontró el elemento : " + pathImage);
            generateWord.addImageToWord(driver);
            driver.close();
            throw t;
        }
    }


    public static void toolkit(String pathImage, String text) throws FindFailed {
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
        screen.find(pathImage).type("v", KeyModifier.CTRL);
    }

    public static void dragDropRegions(String pathImageR1, String pathImageR2) throws FindFailed {
        Region region1 = screen.find(pathImageR1);
        region1.click();
        Region region2 = screen.find(pathImageR2);
        Region lRegion = region1.grow(0, region2.getW() / -2, 0, 0).highlight(3, "red");
        Region rRegion = region2.grow(region2.getW() / -2, 0, 0, 0).highlight(3, "red");
        region2.dragDrop(lRegion, rRegion);
    }

    public static void scrollSikuli(String pathImage, String movimiento, int distancia) throws FindFailed {
        switch (movimiento) {
            case "UP":
                screen.wheel(pathImage, WHEEL_UP, distancia);
                break;
            case "DOWN":
                screen.wheel(pathImage, WHEEL_DOWN, distancia);
                break;
            default:
                System.out.println("solo para scroll Vertical");
        }
    }

    public static boolean validateImage(String pathImage) {
        boolean estado = false;
        if (screen.exists(pathImage) != null) {
            estado = true;
        }
        return estado;
    }

    public static void screenClipUser(String nameCapture) throws IOException {
        Screen screen = Screen.getPrimaryScreen();
        Region region = screen.selectRegion("Selecione el área de captura");
        ScreenImage clip = region.getLastScreenImage();
        javax.imageio.ImageIO.write(clip.getImage(), "PNG", new File("src/main/resources/images/screenshots/" + nameCapture + ".png"));

    }

}
