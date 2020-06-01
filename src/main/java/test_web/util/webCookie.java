package test_web.util;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class webCookie {
    private static RemoteWebDriver driver;

    /**
     * function: 将文件保存的cookie读取并以Set<Cookie>类型返回
     * */
    public static Set<Cookie> getCookie() throws IOException, ParseException {
        FileReader fr = new FileReader("cookie.txt");
        BufferedReader br = new BufferedReader(fr);

        String line;
        Set<Cookie> cookies = new HashSet<Cookie>();
        while ((line = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ";");
            String name = st.nextToken();
            String value = st.nextToken();
            String domain = st.nextToken();
            String path = st.nextToken();
            Date expiry = null;
            String expiryStr = st.nextToken();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
            if (!expiryStr.equals("null")) {
                expiry = sdf.parse(expiryStr);
            }

            boolean isSecure = Boolean.parseBoolean(st.nextToken());
            Cookie cookie = new Cookie(name, value, domain, path, expiry, isSecure);
            System.out.println(cookie.toString());
            cookies.add(cookie);

        }
        ;
        br.close();
        return cookies;
    }

    /**
     * function: 仅获取人工登录企业微信后页面的cookie
     * steps：
     ** 1. 命令行启动浏览器调试进程：chrome --remote-debugging-port=9222 --user-data-dir="G:\code\hogwarts_study\chrome_temp"
     ** 2. 在启动的浏览器调试进程中人工登录一次企业微信
     ** 3. 将获取cookie存到文件备用
     * */
    public static void setCookie() throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
        driver = new ChromeDriver(options);

        FileWriter fw = new FileWriter("cookie.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        for (Cookie cookie : driver.manage().getCookies()) {
            bw.write(
                    cookie.getName() + ";" +
                            cookie.getValue() + ";" +
                            cookie.getDomain() + ";" +
                            cookie.getPath() + ";" +
                            cookie.getExpiry() + ";" +
                            cookie.isSecure()

            );
            bw.newLine();

        };
        bw.close();
        driver.close();
    }
}
