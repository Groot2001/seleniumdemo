package wework;

import org.junit.jupiter.api.*;
import test_web.page.ContactPage;
import test_web.page.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestContact {
    static MainPage main;
    static ContactPage contact;
    @BeforeAll
    static void beforeAll(){
        main = new MainPage();
        contact = main.toContactPage();
    }
    @Order(1)
    @Test
    void addDepartmentTest(){
        String departmentName = contact.addDepartment("2020060101").search("2020060101").getDepartmentName();
        assertEquals(departmentName, "2020060101");
        //System.out.println("测试添加部门");
    }
    @Order(2)
    @Test
    void delDepartmentTest(){
        String departId = main.toContactPage().getDepartmentId("2020060101");
        main.toContactPage().deleteDepartment(departId);
    }

    @Order(3)
    @Test
    void addTag(){
        contact = main.toContactPage();
        contact.addTag("hr");
        String tagName = contact.search("hr").getTagName();
        assertEquals(tagName, "hr");
    }
    @Order(4)
    @Test
    void deleteTag(){
        contact = main.toContactPage();
        contact.deleteTag("hr");

    }

    @AfterAll
    static void afterAll(){
        //contact.quit();
    }
}
