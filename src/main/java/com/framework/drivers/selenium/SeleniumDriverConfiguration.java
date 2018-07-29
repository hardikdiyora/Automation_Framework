
package com.framework.drivers.selenium;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * SeleniumDriver Configuration class that reads seleniumdriver.json file for reading selenium configuration details.
 * Which can be injected in to other classes on a required basis and use
 * selenium configuration when needed.
 *
 */
@Configuration
public class SeleniumDriverConfiguration
{

    private JsonNode _defaults;
    private JsonNode _browsers;

    /***
     * Constructs SeleniumDriverConfiguration object.
     * 
     * @throws Exception exception that gets thrown.
     */
    public SeleniumDriverConfiguration()
        throws Exception
    {

        JsonFactory jf = new JsonFactory();
        ObjectMapper mapper = new ObjectMapper(jf);
        JsonNode tree = mapper.readTree(new File("seleniumdriver.json"));
        _defaults = tree.get("defaults");
        _browsers = tree.get("browsers");
    }

    /***
     * Method to get default browser type.
     * 
     * @return BrowserType.
     */
    public SeleniumDriverType getDefaultBrowser()
    {

        for (final SeleniumDriverType driverType : SeleniumDriverType.values())
        {
            if (driverType.name()
                          .equalsIgnoreCase(_defaults.get("browser")
                                                     .toString()))
            {
                return driverType;
            }
        }
        return SeleniumDriverType.CHROME;
    }

    /**
     * Method to get browsers.
     * 
     * @return the _browsers
     */
    public JsonNode getBrowsers()
    {
        return _browsers;
    }

    /**
     * Method to get defaults.
     * 
     * @return the defaults
     */
    public JsonNode getDefaults()
    {
        return _defaults;
    }
}
