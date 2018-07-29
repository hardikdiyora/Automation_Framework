package com.framework.libs.pages;

import com.framework.drivers.selenium.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PwdRecoveryPage
        extends BasePage
{
    public static By email_tb = By.cssSelector("input[type='email']");
    public static By send_email_btn = By.cssSelector("button[type='submit']");

    public PwdRecoveryPage(WebDriver driver) {
        super(driver);
    }

    /**
     *
     * @param email password recovery for the email.
     * @return LoginPage object
     */
    public LoginPage submitPwdRecoveryForm(String email){
        inputText(email_tb, email);
        click(send_email_btn);
        return new LoginPage(super._driver);
    }
}
