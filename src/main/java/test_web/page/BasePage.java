package test_web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {
    RemoteWebDriver driver;
    WebDriverWait wait;

    public BasePage() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 8);
    }

    public BasePage(RemoteWebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 8);
    }


    public void quit(){
        driver.quit();
    }

    public void click(By by) {
        try{
            wait.until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception e){
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        }
        driver.findElement(by).click();
    }

    public void sendkeys(By by, String text){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).sendKeys(text);
    }

    public void clear(By by){
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        driver.findElement(by).clear();
    }

    public void uploadF(By by, String path){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        driver.findElement(by).sendKeys(path);

    }
}
