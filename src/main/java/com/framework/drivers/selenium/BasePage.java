
package com.framework.drivers.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/****
 * Page Object model pattern, each page class depending on the pages that are in
 * product will extend this BasePage class.
 */
public class BasePage
{

    protected WebDriver _driver = null;
    protected static final Logger LOG = Logger.getLogger(BasePage.class);
    protected WebDriverWait _driverWait;
    protected static long _timeOut = 10;

    /****
     * Base page class that uses page factory model to initialize elements in the derived class.
     * 
     * @param driver Selenium WebDriver
     */
    public BasePage(final WebDriver driver)
    {
        this._driver = driver;
        _driverWait = new WebDriverWait(driver, _timeOut);
    }

    /****
     * Wait's until expected condition is met.
     *
     * @param expectedCondition pass a condition of type ExpectedCondition
     * @param <T> Expected condition type.
     * @return result.
     */
    public <T> T waitUntilExpectedCondition(final ExpectedCondition<T> expectedCondition)
    {
        LOG.info("Wait until expected condition is met.");
        return _driverWait.until(expectedCondition);
    }

    /****
     * Enter the text into text box.
     *
     * @param locator locator of web element.
     * @param text text to be fill in web element like textbox or text area.
     *
    */
    protected void inputText(By locator, String text) {
        waitUntilElementVisible(locator);
        _driver.findElement(locator).sendKeys(text);
        LOG.info("Checking to make sure text is populated");
        waitUntilExpectedCondition(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }

    /****
     * Check whether element is present or not.
     *
     *  @param locator locator of web element.
     */
    protected boolean isElementPresent(By locator) {
        try {
            waitUntilElementVisible(locator);
            _driver.findElement(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /****
     * Wait until element to be visible.
     *
     * @param locator locator of web element.
     */
    protected void waitUntilElementVisible(By locator){
        _driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /****
     * Click on the element.
     *
     * @param locator locator of web element.
     ****/
    protected void click(By locator) {
        _driver.findElement(locator).click();
    }

}
