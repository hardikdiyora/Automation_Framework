
package com.framework.drivers.selenium;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.util.concurrent.TimeUnit;

/**
 * FireFox Driver type class.
 *
 */
public class FirefoxBrowserDriver extends Browser
{
    private JsonNode _fireFoxConfig = _driverConfig.getBrowsers()
                                                   .get("FireFox");

    @Override
    protected WebDriver initDriver()
        throws Exception
    {
        try
        {

            _log.info("Initializing FireFox WebDriver");
            if (!(getOsName().startsWith("Windows")))
            {

                System.setProperty("webdriver.gecko.driver",
                                   getDriverPath(_fireFoxConfig.get("driver_name")
                                                               .asText()));
            }
            else
            {
                System.setProperty("webdriver.gecko.driver",
                                   getDriverPath(_fireFoxConfig.get("windows_driver_name")
                                                               .asText()));
            }
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--no-sandbox");
            GeckoDriverService service = GeckoDriverService.createDefaultService();
            _driver = new FirefoxDriver(service, firefoxOptions);
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
