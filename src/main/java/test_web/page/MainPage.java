package test_web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import test_web.util.webCookie;

import java.io.IOException;
import java.text.ParseException;
import java.util.Set;

public class MainPage extends BasePage {
    public MainPage() {
        super();
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.get(url);
        driver.manage().deleteAllCookies();

        Set<Cookie> cookies = null;
        try {
            cookies = webCookie.getCookie();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        for (Cookie c : cookies) {
            driver.manage().addCookie(c);
        }

        driver.get(url);  //第二次带cookies打开网页
    }
    public ContactPage toContactPage(){
        click(By.cssSelector("#menu_contacts"));
        return new ContactPage(driver);
    }
}
