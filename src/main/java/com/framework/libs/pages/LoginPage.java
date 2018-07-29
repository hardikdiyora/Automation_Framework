package com.framework.libs.pages;

import com.framework.drivers.selenium.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage
        extends BasePage {

    public static By email_tb = By.cssSelector("input[type='email']");
    public static By email_tb_empty = By.cssSelector("div[class$='-input-email auth0-lock-error']");
    public static By password_tb = By.cssSelector("input[type='password']");
    public static By password_tb_empty = By.cssSelector("div[class$='-input-password auth0-lock-error']");
    public static By invalid_credential_msg = By.cssSelector("span[class='animated fadeInUp']");
    public static By login_btn = By.cssSelector("button[type='submit']");
    public static By recover_passwrd_link = By.cssSelector("a[class='auth0-lock-alternative-link']");

    public LoginPage(WebDriver driver) {
        super(driver);
        LOG.info("Opening Login Page");
        waitUntilElementVisible(login_btn);
    }

    /**
     * login to account with email and password.
     * @param email account email
     * @param password account password
     * @return BasePage object
     */
    public BasePage loginToAccount(String email, String password) {
        inputText(email_tb, email);
        inputText(password_tb, password);
        click(login_btn);
        if (email.equals("")) {
            LOG.debug("Email field has empty value.");
            Assert.assertTrue(_driver.findElement(email_tb_empty).isEnabled());
            return new LoginPage(super._driver);
        } else if (password.equals("")) {
            LOG.debug("Password field has empty value.");
            Assert.assertTrue(_driver.findElement(password_tb_empty).isEnabled());
            return new LoginPage(super._driver);
        } else {
            if (isElementPresent(invalid_credential_msg)) {
                LOG.debug("Login form has invalid Credential.");
                Assert.assertTrue(_driver.findElement(invalid_credential_msg).isDisplayed());
                return new LoginPage(super._driver);
            } else {
                LOG.debug("Login form has valid credential.");
                return new MainPage(super._driver);
            }
        }
    }

    /*
        click on password recovery link.
    */
    public PwdRecoveryPage clickRecoverPassword(){
        click(recover_passwrd_link);
        return new PwdRecoveryPage(super._driver);
    }
}
