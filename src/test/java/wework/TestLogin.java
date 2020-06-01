package wework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import test_web.util.webCookie; //类需要在一个包下才能跨包导入

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

public class TestLogin {
    static RemoteWebDriver driver;
    static Set<Cookie> cookies;
    @BeforeAll
    static void setUp(){
        driver = new ChromeDriver();

        try {
            cookies = webCookie.getCookie();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        };

    }
    @Disabled
    @Test
    void testlogin(){
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        for (Cookie c:cookies) {
            driver.manage().addCookie(c);
        }
        driver.get("https://work.weixin.qq.com/wework_admin/frame"); //第二次带cookies打开网页
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@Disabled
    @Test
    void testGetCookie(){
        try {
            webCookie.setCookie();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDown(){
        driver.quit();
    }
}
