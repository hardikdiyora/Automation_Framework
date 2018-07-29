
package com.framework.drivers.selenium;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariDriverService;
import org.openqa.selenium.safari.SafariOptions;

import java.util.concurrent.TimeUnit;

/**
 * Safari Browser Driver type class.
 *
 */
public class SafariBrowserDriver extends Browser
{
    private JsonNode _safariConfig = _driverConfig.getBrowsers()
                                                  .get("Safari");

    @Override
    protected WebDriver initDriver()
        throws Exception
    {
        try
        {

            _log.info("Initializing Safari WebDriver");
            if (!(getOsName().startsWith("Windows")))
            {

                System.setProperty("webdriver.safari.driver",
                                   getDriverPath(_safariConfig.get("driver_name")
                                                              .asText()));
            }
            else
            {
                _log.error("Safari is only supported on Mac or may be unix");
                throw new Exception("Current safari webdriver is not supported Windows OS");
            }
            SafariOptions safariOptions = new SafariOptions();
            SafariDriverService service = SafariDriverService.createDefaultService();
            _driver = new SafariDriver(service, safariOptions);
            _driver.manage()
                   .timeouts()
                   .implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
            _driver.manage()
                   .window()
                   .setPosition(new Point(0, 0));
            _driver.manage()
                   .window()
                   .maximize();
        }
        catch (final Exception e)
        {
            e.printStackTrace();
            throw e;
        }
        finally
        {
            _log.info("Driver service created.");
        }
        return _driver;
    }

}
