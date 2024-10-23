package Test;


import Pages.LoginPage;
import Pages.BasePage;
import org.fluentlenium.core.annotation.Page;
import org.testng.annotations.Test;

import static org.fluentlenium.assertj.FluentLeniumAssertions.assertThat;

public class LoginTest extends BaseTest {

    @Page
    LoginPage loginpage;

    @Page
    BasePage basepage;

  /*  public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        ChromeDriver chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

    }*/


    @Test
    public void loginSuccessful() {


    }

    @Test
    public void newPage() {
        goTo(basepage.URL_MAIN);
        basepage.wait3seconds();
        basepage.goToNewPage(basepage.NEW_URL);
        basepage.wait3seconds();

    }
@Test
    public void fillUsername() {
        goTo(basepage.URL_MAIN);
        basepage.wait3seconds();
        basepage.goToNewPage(basepage.NEW_URL);
        basepage.wait3seconds();
        basepage.fillUserName(basepage.USERNAME);
        basepage.wait3seconds();
    }

    @Test
    public void fillPassword() {
        basepage.goToNewPage(basepage.NEW_URL);
        basepage.fillUserName(basepage.USERNAME);
        basepage.fillPassWord(basepage.PASSWORD);
        basepage.wait3seconds();
    }

@Test
    public void logIn(){
    basepage.goToNewPage(basepage.NEW_URL);
    basepage.fillUserName(basepage.USERNAME);
    basepage.fillPassWord(basepage.PASSWORD);
    basepage.wait3seconds();
    basepage.clickSubmit(basepage.Submit);

}
@Test
    public void sort(){
        logIn();
        basepage.clickElement(basepage.Select);
        basepage.selectDropDown(basepage.Select, basepage.SortText);
}



    @Test
    public  void checkText(){
        basepage.goToNewPage(basepage.NEW_URL);
        basepage.fillUserName(basepage.USERNAME);
        basepage.fillPassWord(basepage.PASSWORD);
        basepage.clickSubmit(basepage.Submit);
        basepage.wait3seconds();
        //BasePage page = newInstance(basepage.checkWidthElement());
        int width = basepage.checkWidthElement("/html/body/div/div/div/div[1]/div[1]/div[2]/div");
        assertThat(width).isEqualTo(basepage.WIDTH_BEREIT);

    }


}