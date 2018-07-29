
package com.framework.drivers.selenium;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;

import java.util.concurrent.TimeUnit;

/**
 * IE DriverType class.
 *
 */
public class IeBrowserDriver extends Browser
{
    private JsonNode _ieConfig = _driverConfig.getBrowsers()
                                              .get("InternetExplorer");

    @Override
    protected WebDriver initDriver()
        throws Exception
    {
        try
        {

            _log.info("Initializing IE WebDriver");
            if (!(getOsName().startsWith("Windows")))
            {
                _log.error("Not a supported OS for IE WebDriver");
                throw new Exception("Microsoft IE webdriver is not avaliable for non windows platforms");

            }
            else
            {
                System.setProperty("webdriver.ie.driver",
                                   getDriverPath(_ieConfig.get("windows_driver_name")
                                                          .asText()));
            }
            InternetExplorerOptions ieOptions = new InternetExplorerOptions();
            InternetExplorerDriverService service = InternetExplorerDriverService.createDefaultService();
            _driver = new InternetExplorerDriver(service, ieOptions);
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
        return _driver;
    }

}
