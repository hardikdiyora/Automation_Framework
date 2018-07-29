
package com.framework.drivers.selenium;

import com.framework.environment.TAFLogger;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

/****
 * Browser abstract class used for creating different _browsers types of selenium web drivers.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Browser
{
    protected WebDriver _driver;
    protected static final long IMPLICIT_WAIT_TIME = 30;
    protected AnnotationConfigApplicationContext _browserConfigurationContext =
        new AnnotationConfigApplicationContext(SeleniumDriverConfiguration.class, TAFLogger.class);
    protected Logger _log = _browserConfigurationContext.getBean(TAFLogger.class)
                                                        .getLogger(getClass());
    protected SeleniumDriverConfiguration _driverConfig =
        _browserConfigurationContext.getBean(SeleniumDriverConfiguration.class);
    private String _os = null;

    protected abstract WebDriver initDriver()
        throws Exception;

    protected String getOsName()
    {
        if (_os == null)
        {
            _os = System.getProperty("os.name");
        }
        _log.info("OS determined by selennium " + _os);
        return _os;
    }

    protected String getDriverPath(final String driverName)
        throws JsonProcessingException, IOException
    {
        String driverBasease = _driverConfig.getDefaults()
                                            .get("driver_location")
                                            .toString()
                                            .replaceAll("\"", "");
        String driverLocation = Paths.get(driverBasease, driverName)
                                     .toString();
        File driver = new File(driverLocation);
        if (driver.exists() && !driver.isDirectory())
        {
            return driver.toString();
        }
        else
        {
            _log.error("Looks like drivers are not installed on your system at "
                       + driverLocation
                       + ". Please download corresponding driver from "
                       + "https://www.seleniumhq.org/download/ and place it in the driver_location mentioned in "
                       + "seleniumdriver.json or edit the location in json according to your OS. "
                       + "Download URL points to the latest available "
                       + "as of today.");
            throw new FileNotFoundException();
        }
    }

}
