package com.framework.libs.pages;

import com.framework.drivers.selenium.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    public static By welcome_section = By.cssSelector("div[class='welcome'");
    public static By upcoming_section = By.cssSelector("div[class='upcoming']");
    public static By open_menu = By.cssSelector("i[class='fa fa-angle-down']");
    public static By log_out = By.cssSelector("i[class='fa fa-sign-out']");

    public MainPage(WebDriver driver){
        super(driver);
        waitUntilElementVisible(welcome_section);
    }

    /**
     * check whether upcoming product section is visible.
     * @return boolean value
     */
    public Boolean checkUpcomingProductSectionPresent(){
        waitUntilElementVisible(upcoming_section);
        return isElementPresent(upcoming_section);
    }

    /**
     * Logout from the account
     * @return LoginPage Object
     */
    public LoginPage logOut(){
        click(open_menu);
        click(log_out);
        return new LoginPage(super._driver);
    }
}
