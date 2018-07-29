/**
 * 
 */
package com.framework.drivers.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Selenium Driver utility class for executing methods or different browser drivers.
 *
 */
public final class SeleniumDriver
{
    private static Logger _log = Logger.getLogger(SeleniumDriver.class);
    private static WebDriver _driver = null;

    private SeleniumDriver()
    {

    }

    /***
     * This method returns the requested driver type.
     * 
     * @param driverType driver type requested.
     * @return _driver return requested driver type
     * @throws Exception exception that get's thrown.
     */
    public static WebDriver getBrowserDriver(final SeleniumDriverType driverType)
        throws Exception
    {

        switch (driverType)
        {
        case CHROME:
            _driver = new ChromeBrowserDriver().initDriver();
            break;
        case FIREFOX:
            _driver = new FirefoxBrowserDriver().initDriver();
            break;
        case SAFARI:
            _driver = new SafariBrowserDriver().initDriver();
            break;
        case IE:
            _driver = new IeBrowserDriver().initDriver();
            break;

        default:
            _driver = new ChromeBrowserDriver().initDriver();
            break;
        }
        return _driver;

    }

    /***
     * closes the browser that is opened.
     */
    public static void quitBrowser()
    {
        if (null != _driver)
        {
            _driver.quit();
            _driver = null;
        }
    }

    /***
     * Refreshes the active browser.
     */
    public static void refresh()
    {
        if (null != _driver)
        {
            _driver.navigate()
                   .refresh();

        }
        else
        {
            throw new NullPointerException("Looks like _driver object is not instantiated.");
        }
    }
}
