
package com.framework.drivers.selenium;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * Chrome Browser Driver type class.
 *
 */
public class ChromeBrowserDriver extends Browser
{
    private JsonNode _chromeConfig = _driverConfig.getBrowsers()
                                                  .get("Chrome");

    private ArrayNode _chromeArguments = (ArrayNode) _chromeConfig.get("arguments");

    @Override
    protected WebDriver initDriver()
        throws Exception
    {
        try
        {

            _log.info("Initializing Chrome WebDriver");
            if (!(getOsName().startsWith("Windows")))
            {
                System.setProperty("webdriver.chrome.driver",
                                   getDriverPath(_chromeConfig.get("driver_name")
                                                              .asText()));
            }
            else
            {
                System.setProperty("webdriver.chrome.driver",
                                   getDriverPath(_chromeConfig.get("windows_driver_name")
                                                              .asText()));
            }
            ChromeOptions chromeOptions = new ChromeOptions();
            for (final JsonNode args : _chromeArguments)
            {
                chromeOptions.addArguments(args.asText());
            }
            ChromeDriverService service = ChromeDriverService.createDefaultService();
            _driver = new ChromeDriver(service, chromeOptions);
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
