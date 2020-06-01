package test_web.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class ContactPage extends BasePage {
    By addBtn = By.cssSelector(".member_colLeft_top_addBtn");
    By addDepartment = By.cssSelector(".js_create_party");

    By addMember=By.xpath("//a[contains(text(),'添加成员')]");
    By username=By.name("username");
    By delete=By.linkText("删除");

    public ContactPage(RemoteWebDriver driver) {
        super(driver);
    }

    public ContactPage addDepartment(String departName){
        click(addBtn);
        click(addDepartment);
        sendkeys(By.name("name"), departName);
        click(By.linkText("选择所属部门"));
        click(By.cssSelector(".jstree-2 #\\31 688851243456566_anchor"));  //选择根部门
        click(By.linkText("确定"));

        return this;
    }

    public String getDepartmentName(){

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("party_name")));
        String departmentName = driver.findElement(By.id("party_name")).getText();
        return departmentName;
    }

    public String getDepartmentId(String departName){
        clear(By.id("memberSearchInput"));
        search(departName);
        String departid = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='ww_searchResult_item_Curr']")))
                .getAttribute("data-id"); //获取部门id
        return departid;
    }

    public ContactPage addMember(String username, String acctid, String mobile) {
        while(driver.findElements(this.username).size()==0){
            click(addMember);
        }

        sendkeys(this.username, username);
        sendkeys(By.name("acctid"), acctid);
        sendkeys(By.name("mobile"), mobile);
        click(By.cssSelector(".js_btn_save"));
        return this;
    }

    public ContactPage deleteDepartment(String departid){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //先删除此部门下所有人员
        /*search(departName);
        click(By.xpath("//input[@type='checkbox']"));
        click(By.cssSelector(".js_delete"));
        click(By.linkText("确认"));*/
        //退回到所有部门列表，删指定部门
        click(By.xpath(String.format("//a[@id='%s_anchor']/span", departid))); //利用获取的部门id定位到三点号菜单
        click(By.xpath("//ul//a[contains(text(),'删除')]"));
        click(By.linkText("确定"));
        return this;
    }

    public ContactPage addTag(String tagName){
        click(By.linkText("标签"));
        click(By.linkText("添加标签"));
        sendkeys(By.name("name"), tagName);
        click(By.linkText("确定"));

        return this;
    }

    public ContactPage deleteTag(String tagName){
        click(By.linkText("标签"));
        String departid = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(tagName)))
                .getAttribute("tag-id");
        click(By.xpath(String.format("//a[@tag-id='%s']", departid)));
        click(By.linkText("删除"));
        return this;
    }

    public String getTagName(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='member_tag_details']//span[@class='ww_commonCntHead_title_inner_text']")));
        String tagName = driver.findElement(By.xpath("//div[@class='member_tag_details']//span[@class='ww_commonCntHead_title_inner_text']")).getText();
        return tagName;
    }

    public ContactPage search(String keyword){
        sendkeys(By.id("memberSearchInput"), keyword);
        return this;
    }
}
