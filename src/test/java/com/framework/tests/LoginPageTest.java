package com.framework.tests;

import com.framework.libs.pages.LoginPage;
import com.framework.libs.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginPageTest
        extends TestBase
{

    private LoginPage _loginPage;

    @BeforeMethod
    void setUp(){
        _driver.get("<PLEASE ENTER THE WEBSITE URL>");
    }

    @Test
    void verifyLoginSuccessFully(){
        LOG.info("Verify a user is able to login successfully.");
        _loginPage = new LoginPage(_driver);
        LOG.info("Logging into account with valid credentials.");
        MainPage _mainPage = (MainPage)_loginPage.loginToAccount("##############", "###########");
        Assert.assertTrue(_mainPage.checkUpcomingProductSectionPresent());
        LOG.info("Log out from Account.");
        _mainPage.logOut();
    }
}
