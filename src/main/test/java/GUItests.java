import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;

import java.util.LinkedList;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@RunWith(Parallelized.class)
public class GUItests {
    String website = "http://localhost:8080/BankWebApp_war_exploded/";

    private String platform;
    private String browserName;
    private String browserVersion;


    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<String[]>();
        //Here you specify the browsers to run. Comment out the browsers that you don't have installed.
        //IMPORTANT!! Changed Mike to role NEW_USER in sql so can run all 3 tests for change PW.
        env.add(new String[]{System.getProperty("os.name"), "chrome", "latest"});
        env.add(new String[]{System.getProperty("os.name"), "firefox", "latest"});
        env.add(new String[]{System.getProperty("os.name"), "ie", "latest"});


        return env;
    }

    public GUItests(String platform, String browserName, String browserVersion) {
        this.platform = platform;
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    @BeforeClass
    public static void BeforeClass(){
        //If you are behind a proxy then configure it here with .proxy etc otherwise the drivers might not get downloaded/configured.
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        WebDriverManager.edgedriver().setup();              //If tests in edge fail for some reason then run it seperately from the other 2. I have stabilised it on my PC but its edge...

    }

    @Before
    public void Before(){
        if (browserName == "chrome"){
            Configuration.browser = "chrome";
        } else if (browserName == "firefox"){
            Configuration.browser = "firefox";
        } else {
            Configuration.browser = "edge";
            Selenide.sleep(3000);
        }
    }

    public void Login(String username,String password){
        Selenide.$(By.name("username")).waitUntil(Condition.visible, 5000).setValue(username);
        Selenide.$(By.name("password")).waitUntil(Condition.visible, 5000).setValue(password);
        Selenide.$(By.name("submit")).waitUntil(Condition.visible, 5000).click();
    }

    public void Logout(){
        Selenide.$(By.partialLinkText("Logout")).waitUntil(Condition.visible, 5000).click();
        Assert.assertTrue(Selenide.$(By.className("alert-info")).waitUntil(Condition.visible, 5000).getText().equals("You've been logged out successfully."));
    }

    public void ChangePW(String newpassword){
        Selenide.$(By.name("password")).waitUntil(Condition.visible, 5000).setValue(newpassword);
        Selenide.$(By.name("confirmPassword")).waitUntil(Condition.visible, 5000).setValue(newpassword);
        Selenide.$(By.name("change")).waitUntil(Condition.visible, 5000).click();
    }


    @org.junit.Test
    public void LoginTest(){
        Selenide.open(website);
        Login("Jake","Jake");
        Logout();
        Login("Jake","WrongPassword");
        Assert.assertTrue(Selenide.$(By.className("alert-danger")).waitUntil(Condition.visible, 5000).getText().equals("Invalid username or password!"));

    }


    @org.junit.Test
    public void ChangePWonLogin(){
        Selenide.open(website);

        if (browserName == "chrome"){
            Login("Sara","Sara");
        } else if (browserName == "firefox"){
            Login("Tom","Tom");
        } else {
            Selenide.sleep(1000);
            Login("Mike","Mike");
            Selenide.sleep(1000);
        }

        ChangePW("12345");

        if (browserName == "chrome"){
            Login("Sara","12345");
        } else if (browserName == "firefox"){
            Login("Tom","12345");
        } else {
            Selenide.sleep(1000);
            Login("Mike","12345");
            Selenide.sleep(1000);
        }

        Logout();
    }


    @org.junit.Test
    public void ViewBalances(){
        Selenide.open(website);
        Login("Jake","Jake");
        Selenide.$$(".table thead tr th").shouldHave(texts("Account number","Balance"));
        Selenide.$$(".table tbody tr td").shouldHave(texts("# 79167592","200.0 EUR","# 92567261","3020.0 EUR"));
        Logout();
    }

    @After
    public void After(){
        getWebDriver().quit();
    }

}
