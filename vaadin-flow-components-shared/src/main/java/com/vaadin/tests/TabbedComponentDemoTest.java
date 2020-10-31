package com.vaadin.tests;

import org.junit.AfterClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;

public abstract class TabbedComponentDemoTest extends com.vaadin.flow.demo.TabbedComponentDemoTest {

    private static SharedBrowser browser = new SharedBrowser();

    @Override
    public void setup() throws Exception {
        browser.setup(super::setup, this::setDriver, this::getDriver, screenshotOnFailure);
    }

    protected int getDeploymentPort() {
        return 8080;
    }

    @Override
    protected List<DesiredCapabilities> getHubBrowsersToTest() {
        return browser.getGridBrowsers().orElse(super.getHubBrowsersToTest());
    }

    @AfterClass
    public static void runAfterTest() {
        browser.clear();
    }
}