import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.util.NoSuchElementException;

public class Selenium {
    //All Static final strings in one place
    public static final String SUTINKU_BUTTON_XPATH = "/html/body/div[2]/div[2]/div[3]/span/div/div/div/div[3]/button[2]/div";
    public static final String SEARCH_TAB_XPATH = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input";
    public static final String GOOGLE_SEARCH_BUTTON_XPATH = "/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]";
    public static final String WEBSITE_XPATH = "//h3[contains(.,'Populiariausi Minecraft Serveriai: M-Craft')]";
    //Desired server to vote on
    public static final String DESIRED_SERVER_XPATH = "//h3[contains(.,'MC.MEEMSO.EU | FACTIONS | McMMO')]";
    public static final String VOTE_BUTTON_XPATH = "//div[2]/div/button";
    public static final String USERNAME_TAB_XPATH = "//*[@id=\"player\"]";
    public static final String FINAL_VOTE_BUTTON_XPATH = "//button[contains(.,'Balsuoti')]";
    public static final String FIND_BY_CLASS_NAME = "text-red-400";
    static WebDriver browser;

    //Run it in main or test folder
    public static void main(String[] args) {
        System.out.println("Vote ant mc servo");
        setup();
        searchByKeyword("m craft");
        openLink();
        openServer();
        vote();
        robotTest("ChicoMono"); //Change username if wanted
        ultimateTest();
        finalClick();
        close();
    }

    public static void setup() {
        //Sync code with selenium and open browser with addBlock
        //Change chromedriver97.exe with newer version when needed
        System.setProperty("webdriver.chrome.driver", "src/webdriver/chromedriver97.exe");
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/block/uBlock-Origin.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        //Creating browser object with accordingly downloaded drivers
        browser = new ChromeDriver(capabilities);
        //Delay for 2 seconds so it would not crash
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Specified browser url
        browser.get("https://www.google.com/");
        //Path to button
        WebElement searchBtn = browser.findElement(By.xpath(SUTINKU_BUTTON_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", searchBtn);
    }

    public static void searchByKeyword(String text) {
        //Path to the search tab
        WebElement searchfield = browser.findElement(By.xpath(SEARCH_TAB_XPATH));
        //Sends desired text to it
        searchfield.sendKeys(text);
        //Path to the search button
        WebElement searchBtn = browser.findElement(By.xpath(GOOGLE_SEARCH_BUTTON_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", searchBtn);
    }

    public static void openLink() {
        //Delay loading to the page
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Path to website
        WebElement website = browser.findElement(By.xpath(WEBSITE_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", website);
    }

    public static void openServer() {
        //Delay loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Path to the best server
        WebElement server = browser.findElement(By.xpath(DESIRED_SERVER_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", server);
    }

    public static void vote() {
        //Delay loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Path to vote button
        WebElement vote = browser.findElement(By.xpath(VOTE_BUTTON_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", vote);
    }

    public static void robotTest(String username) {
        //Delay loading
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Path to username tab
        WebElement webUsername = browser.findElement(By.xpath(USERNAME_TAB_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", webUsername);
        //Sends desired text to it
        webUsername.sendKeys(username);
    }

    public static void ultimateTest() {
        WebElement usernameTab = browser.findElement(By.xpath(USERNAME_TAB_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", usernameTab);
        Actions reCaptcha = new Actions(browser);
        //Delay loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        reCaptcha.sendKeys(Keys.TAB).perform();
        //Delay loading
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        reCaptcha.sendKeys(Keys.ENTER).perform();
    }

    public static boolean finalClick() {
        //Delay to solve it manually if needed
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //Path to button
        WebElement click = browser.findElement(By.xpath(FINAL_VOTE_BUTTON_XPATH));
        //Presses with javaScript
        JavascriptExecutor executor = (JavascriptExecutor) browser;
        executor.executeScript("arguments[0].click();", click);
        return endResult();
    }

    public static boolean endResult() {
        boolean fail;
        //Delay loading
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            browser.findElement(By.className(FIND_BY_CLASS_NAME));
            fail = false;
            System.out.println("Failed wait 24 hours and try again");
        } catch (NoSuchElementException e) {
            fail = true;
            System.out.println("Worked thank you for voting");
        }
        return fail;
    }

    public static void close() {
        browser.close();
    }
}