package com.tsoft.bot.frontend.baseClass.web;

import com.tsoft.bot.frontend.exceptions.FrontEndException;
import com.tsoft.bot.frontend.utility.ExtentReportUtil;
import com.tsoft.bot.frontend.utility.GenerateWord;
import com.tsoft.bot.frontend.utility.Sleeper;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    static GenerateWord generateWord = new GenerateWord();
    private static final long MAX_WAIT_TIME_SECONDS = 250;
    private static final long NEXT_TRY_TIME_MILISECONDS = 200;


    public static void hoverElement(WebDriver driver, String key, String locator ){
        Actions actions = new Actions(driver);

        switch (key.toLowerCase()) {

            case "id":
                actions.moveToElement(new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.id(locator)))).perform();
                break;

            case "name":
                actions.moveToElement(new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.name(locator)))).perform();
                break;

            case "css":
                actions.moveToElement(new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)))).perform();
                break;

            case "linktext":
                actions.moveToElement(new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)))).perform();
                break;

            case "xpath":
                actions.moveToElement(new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)))).perform();
                break;
        }
    }

    public static void hoverElement(WebDriver driver, WebElement webElement ){
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }

    public static void sendKeyValue(WebDriver driver, String key, String locator, String value) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).sendKeys(value);
                    break;

                case "name":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).sendKeys(value);
                    break;

                case "css":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator))).sendKeys(value);
                    break;

                case "linktext":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).sendKeys(value);
                    break;

                case "xpath":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).sendKeys(value);
                    break;
            }

        } catch (Throwable t) {

            generateWord.sendText("No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }

    public static void click(WebDriver driver, String key, String locator) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).click();
                    break;

                case "name":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).click();
                    break;

                case "css":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator))).click();
                    break;

                case "linktext":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).click();
                    break;

                case "xpath":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).click();
                    break;
            }

        } catch (Throwable t) {

            generateWord.sendText("Error : No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }
    public static void click(WebElement webElement) throws Throwable {
        webElement.click();
    }


    public static void clear(WebDriver driver, String key, String locator) throws Throwable {
        try {

            switch (key.toLowerCase()) {

                case "id":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.id(locator))).clear();
                    break;

                case "name":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.name(locator))).clear();
                    break;

                case "css":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.elementToBeClickable(By.cssSelector(locator))).clear();
                    break;

                case "linktext":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator))).clear();
                    break;

                case "xpath":
                    new WebDriverWait(driver, 20, 40).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).clear();
                    break;
            }
        } catch (Throwable t) {

            generateWord.sendText("Error : No se encontro el elemento : " + locator);

            generateWord.addImageToWord(driver);

            handleError(driver, "", "No se encontro el elemento : " + locator);

            driver.close();

            throw t;
        }
    }

    public static void selectByVisibleText(WebDriver driver, String locator, String value) throws Throwable {

        String found = value;

        Select select = new Select(driver.findElement(By.id(locator)));

        List<WebElement> options = select.getOptions();

        if (CollectionUtils.isNotEmpty(options)) {

            for (WebElement option : options) {

                if (StringUtils.equalsIgnoreCase(option.getText(), locator)) {

                    found = option.getText();

                    break;
                }
            }
        }

        if (Objects.isNull(found)) {

            handleError(driver, "", "No se encontro el elemento : " + locator);

            generateWord.sendText("No se encontró el elemento : " + locator);

            generateWord.addImageToWord(driver);
        }

        try {
            select.selectByVisibleText(found);
        }catch (NoSuchElementException ext){
            String msg ;
            if(getOptions(driver, "id", locator).size()==0){
                msg =  "El elmento no tiene opciones para seleccionar.";
            }else{
                msg =  "La opción  '"+value+"'   no se encuentra dentro de la lista.";
            }
            throw new FrontEndException("", msg);
        }
    }

    public static String getNumberOfCaracter(String caracter) {

        char[] caracterArray = caracter.toCharArray();

        StringBuilder number = new StringBuilder(StringUtils.EMPTY);

        for (char c : caracterArray) {

            if (Character.isDigit(c)) {

                number.append(c);

            }
        }
        return number.toString();
    }

    public static Exception handleError(WebDriver driver, String codigo, String msg) throws Throwable {
        stepWarning(driver, msg);
        return new FrontEndException(StringUtils.trimToEmpty(codigo), msg);
    }

    public static String waitForElementNotVisible(WebDriver driver, String key, String locator) throws Throwable {
        if ((driver == null) || (locator == null) || locator.isEmpty()) { return "[Error : Mal uso del metodo waitForElementNotVisible()]"; }
        try {
            switch (key.toLowerCase()) {

                case "id":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.id(locator)));
                    break;

                case "name":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.name(locator)));
                    break;

                case "css":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator)));
                    break;

                case "linktext":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.linkText(locator)));
                    break;

                case "xpath":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(locator)));
                    break;
            }
            return null;
        } catch (TimeoutException e) {
            generateWord.sendText("Se excedió el tiempo de espera para que se desaparezca el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "Se excedió el tiempo de espera para que se desaparezca el elemento : " + locator);
            driver.close();
            throw e;
        }
    }

    public static String waitForPresenceOfElement(WebDriver driver, String key, String locator) throws Throwable {
        if ((driver == null) || (locator == null) || locator.isEmpty()) { return "[Error : Mal uso del metodo waitForPresenceOfElement()]"; }
        try {
            switch (key.toLowerCase()) {

                case "id":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
                    break;

                case "name":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
                    break;

                case "css":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
                    break;

                case "linktext":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
                    break;

                case "xpath":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                    break;
            }
            return null;
        } catch (TimeoutException e) {
            generateWord.sendText("Se excedió el tiempo de espera para que se visualice el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "Se excedió el tiempo de espera para que se visualice el elemento : " + locator);
            driver.close();
            throw e;
        }
    }

    public static void selectByValue(WebDriver driver, String key, String locator, String value) throws Throwable {
        Select select = null;
        try {
            switch (key.toLowerCase()) {
                case "id":
                    select = new Select(driver.findElement(By.id(locator)));
                    break;
                case "name":
                    select = new Select(driver.findElement(By.name(locator)));
                    break;
                case "css":
                    select = new Select(driver.findElement(By.cssSelector(locator)));
                    break;
                case "linktext":
                    select = new Select(driver.findElement(By.linkText(locator)));
                    break;
                case "xpath":
                    select = new Select(driver.findElement(By.xpath(locator)));
                    break;
            }
            assert select != null;
            select.selectByValue(value);
        } catch (Throwable t) {
            stepWarning(driver, "No encontró el elemento " + locator);
            throw new FrontEndException("BC-001", "No encontró el elemento " + locator);
        }

    }

    public static void selectByIndex(WebDriver driver, String key, String locator, int index) throws Throwable {
        Select select = null;
        try {
            switch (key.toLowerCase()) {
                case "id":
                    select = new Select(driver.findElement(By.id(locator)));
                    break;
                case "name":
                    select = new Select(driver.findElement(By.name(locator)));
                    break;
                case "css":
                    select = new Select(driver.findElement(By.cssSelector(locator)));
                    break;
                case "linktext":
                    select = new Select(driver.findElement(By.linkText(locator)));
                    break;
                case "xpath":
                    select = new Select(driver.findElement(By.xpath(locator)));
                    break;
            }
            assert select != null;
            select.selectByIndex(index);
        } catch (Throwable t) {
            stepWarning(driver, "No encontró el elemento " + locator);
            throw new FrontEndException("BC-001", "No encontró el elemento " + locator);
        }

    }

    public static List<WebElement> getOptions(WebDriver driver, String key, String locator) throws Throwable {
        Select select = null;
        try {
            switch (key.toLowerCase()) {
                case "id":
                    select = new Select(driver.findElement(By.id(locator)));
                    break;
                case "name":
                    select = new Select(driver.findElement(By.name(locator)));
                    break;
                case "css":
                    select = new Select(driver.findElement(By.cssSelector(locator)));
                    break;
                case "linktext":
                    select = new Select(driver.findElement(By.linkText(locator)));
                    break;
                case "xpath":
                    select = new Select(driver.findElement(By.xpath(locator)));
                    break;
            }
            if(Objects.isNull(select)) throw new Exception("");
            return select.getOptions();
        } catch (Throwable t) {
            stepWarning(driver, "No encontró el elemento " + locator);
            throw new FrontEndException("BC-001", "No encontró el elemento " + locator);
        }

    }

    public static WebElement getFirstSelectedOption(WebDriver driver, String key, String locator) throws Throwable {
        Select select = null;
        try {
            switch (key.toLowerCase()) {
                case "id":
                    select = new Select(driver.findElement(By.id(locator)));
                    break;
                case "name":
                    select = new Select(driver.findElement(By.name(locator)));
                    break;
                case "css":
                    select = new Select(driver.findElement(By.cssSelector(locator)));
                    break;
                case "linktext":
                    select = new Select(driver.findElement(By.linkText(locator)));
                    break;
                case "xpath":
                    select = new Select(driver.findElement(By.xpath(locator)));
                    break;
            }
            if(Objects.isNull(select)) throw new Exception("");
            return select.getFirstSelectedOption();
        } catch (Throwable t) {
            stepWarning(driver, "No encontró el elemento " + locator);
            throw new FrontEndException("BC-001", "No encontró el elemento " + locator);
        }

    }

    //Para comparaServicios
    public static String getText(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement = null;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    new FrontEndException("BC-005", "No se encontro :" + webElement);
            }
            assert webElement != null;
            return StringUtils.trimToEmpty(webElement.getText());

        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            throw t;
        }
    }

    public static Boolean isEnabled(WebDriver driver, String key, String locator) throws Throwable {
        try{
            WebElement webElement;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    webElement = null;
            }
            if (Objects.isNull(webElement)) throw new FrontEndException("BC-012", "No se encontra elemento" + locator);
            return webElement.isEnabled();
        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public static Boolean isDisplayed(WebDriver driver, String key, String locator)  {
        boolean existe = false;
        try{
            switch (key.toLowerCase()) {
                case "id":
                    existe = driver.findElement(By.id(locator)).isDisplayed();
                    break;
                case "name":
                    existe = driver.findElement(By.name(locator)).isDisplayed();
                    break;
                case "css":
                    existe = driver.findElement(By.cssSelector(locator)).isDisplayed();
                    break;
                case "linktext":
                    existe = driver.findElement(By.linkText(locator)).isDisplayed();
                    break;
                case "xpath":
                    existe = driver.findElement(By.xpath(locator)).isDisplayed();
                    break;
                default:
                    existe =  false;
            }
            return existe;
        }catch (NoSuchElementException nE){
            return existe;
        }
    }

    public static Boolean isSelected(WebDriver driver, String key, String locator) throws Throwable {
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        WebElement webElement = null;
        try{
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
            }

            return webElement.isSelected();
        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public static String getValueElement(WebDriver driver, String key, String locator) throws Throwable{
        try{
            WebElement webElement = null;
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
                default:
                    new FrontEndException("BC-005", "No se encontro :" + webElement);
            }
            assert webElement != null;
            return webElement.getAttribute("value");
        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }



    public static List<WebElement> getElements(WebDriver driver, String key, String locator) throws Throwable {
        List<WebElement> webElements = null;
        try{
            switch (key.toLowerCase()) {
                case "id":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
                    webElements = driver.findElements(By.id(locator));
                    break;
                case "name":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.name(locator)));
                    webElements = driver.findElements(By.name(locator));
                    break;
                case "css":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
                    webElements = driver.findElements(By.cssSelector(locator));
                    break;
                case "linktext":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.linkText(locator)));
                    webElements = driver.findElements(By.linkText(locator));
                    break;
                case "xpath":
                    new WebDriverWait(driver, 40, 80).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                    webElements = driver.findElements(By.xpath(locator));
                    break;
            }
            return webElements;
        }catch (Throwable t){
            generateWord.sendText("No se encontraron elementos : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontraron elementos : " + locator);
            driver.close();
            throw t;
        }
    }

    /*

    public static List<WebElement> getElements(WebDriver driver, String key, String locator) throws Throwable {
            List<WebElement> webElements = null;
        try{
            switch (key.toLowerCase()) {
                case "id":
                    webElements = driver.findElements(By.id(locator));
                    break;
                case "name":
                    webElements = driver.findElements(By.name(locator));
                    break;
                case "css":
                    webElements = driver.findElements(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElements = driver.findElements(By.linkText(locator));
                    break;
                case "xpath":
                    webElements = driver.findElements(By.xpath(locator));
                    break;
            }
            return webElements;
        }catch (Throwable t){
            generateWord.sendText("No se encontraron elementos : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontraron elementos : " + locator);
            driver.close();
            throw t;
        }
    }


*/


    public static String waitForVisibilityOfElement(WebDriver driver, String key, String locator) throws Throwable {
        if ((driver == null) || (locator == null) || locator.isEmpty()) { return "[Error : Mal uso del metodo waitForPresenceOfElement()]"; }
        try {
            switch (key.toLowerCase()) {

                case "id":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locator))));
                    break;

                case "name":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locator))));
                    break;

                case "css":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locator))));
                    break;

                case "linktext":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locator))));
                    break;

                case "xpath":
                    (new WebDriverWait(driver, MAX_WAIT_TIME_SECONDS, NEXT_TRY_TIME_MILISECONDS)).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(locator))));
                    break;
            }
            return null;
        } catch (TimeoutException e) {
            generateWord.sendText("Se excedió el tiempo de espera para que se visualice el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "Se excedió el tiempo de espera para que se visualice el elemento : " + locator);
            driver.close();
            throw e;
        }
    }



    public static void moveToElement(WebDriver driver, String key, String locator) throws Throwable{
        WebElement webElement = null;
        try{
            switch (key.toLowerCase()) {
                case "id":
                    webElement = driver.findElement(By.id(locator));
                    break;
                case "name":
                    webElement = driver.findElement(By.name(locator));
                    break;
                case "css":
                    webElement = driver.findElement(By.cssSelector(locator));
                    break;
                case "linktext":
                    webElement = driver.findElement(By.linkText(locator));
                    break;
                case "xpath":
                    webElement = driver.findElement(By.xpath(locator));
                    break;
            }

            Actions actions = new Actions(driver);
            actions.moveToElement(webElement).perform();

        }catch (Throwable t){
            generateWord.sendText("No se encontró el elemento : " + locator);
            generateWord.addImageToWord(driver);
            handleError(driver, "", "No se encontro el elemento : " + locator);
            driver.close();
            throw t;
        }
    }

    public static void scrollBar(WebDriver driver, int size, int sleep) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("window.scrollBy(0, " + size + ")");
        sleep(sleep);
    }


    public static void scrollBar(WebDriver driver, int size) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("window.scrollBy(0, " + size + ")");
    }

    public static void scrollBarElement(WebDriver driver, String idElement, int size) {
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        System.out.println("document.getElementById('" + idElement + "').scrollBy(0, " + size + ")");
        ev.executeScript("document.getElementById('" + idElement + "').scrollBy(0, " + size + ")");
    }

    public static void scrollIntoView(WebDriver driver, WebElement webElement){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", webElement);
    }

    public static void executeJavaScript(WebDriver driver, String script){
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript(script);
    }

    public static void clickJsID(WebDriver driver, String idLocator){
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("document.getElementById('"+idLocator+"').click();");
        sleep(2000);
    }

    public static void hideSubMenuBF(WebDriver driver, String idLocator){
        JavascriptExecutor ev = (JavascriptExecutor) driver;
        ev.executeScript("arguments[0].setAttribute('style', 'visibility: hidden !important')",
                driver.findElement(By.xpath(idLocator)));
    }

    public static void sleep(int milisegundos) {
        Sleeper.Sleep(milisegundos);
    }

    public static void sleep2(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stepPass(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepPass(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepWarning(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepWarning(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepFail(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepFail(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepError(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepError(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepInfo(WebDriver driver, String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepInfo(driver, descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepInfo(String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepInfo(descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepWarningNoShoot(String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepWarningNoShoot(descripcion);
        } catch (Throwable t) {
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }

    public static void stepFailNoShoot(String descripcion) throws Throwable {
        try {
            ExtentReportUtil.INSTANCE.stepFailNoShoot(descripcion);
        }catch (Throwable t){
            System.out.println(Arrays.toString(t.getStackTrace()));
            throw t;
        }
    }




    public void switchToFrame(WebDriver driver,  String frameName){
        driver.switchTo().frame(frameName);
    }

    public void switchToParentFrame(WebDriver driver){
        driver.switchTo().parentFrame();
    }


}

