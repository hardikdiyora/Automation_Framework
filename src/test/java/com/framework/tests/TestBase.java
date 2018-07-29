package com.framework.tests;

import com.framework.drivers.selenium.SeleniumDriver;
import com.framework.drivers.selenium.SeleniumDriverConfiguration;
import com.framework.drivers.selenium.SeleniumDriverType;
import com.framework.environment.Environment;
import com.framework.environment.TAFLogger;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected Environment _environment;
    protected Logger LOG;
    protected SeleniumDriverType _defaultBrowserType;
    protected WebDriver _driver;

    /**
     * Initialization of the Environment from env.json
     *
     * @param ctx contains test description
     * @throws Exception if something goes wrong
     */
    @BeforeClass(alwaysRun = true)
    public void initEnvironment(final ITestContext ctx)
            throws Exception
    {

        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Environment.class,
                TAFLogger.class,
                SeleniumDriverConfiguration.class);
         _environment = context.getBean(Environment.class);
        TAFLogger tafLogger = context.getBean(TAFLogger.class);
        LOG = tafLogger.getLogger(getClass());

        LOG.info("Environment initalized to:" + _environment.getEnvDetails());
        LOG.info("Opening the Browser.");
        _defaultBrowserType = new SeleniumDriverConfiguration().getDefaultBrowser();
        _driver = SeleniumDriver.getBrowserDriver(_defaultBrowserType);
    }

    @AfterClass
    public void tearDown(){
        LOG.info("Closing the Browser.");
        SeleniumDriver.quitBrowser();
    }
}