package edu.ncsu.csc.itrust2.web;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserHandler {

    private static boolean Mac() {
        return OS.contains("Mac OS X");
    }

    private static boolean Linux() {
        return OS.contains("Linux");
    }

    private static boolean Windows() {
        return OS.contains("Windows");
    }

    private final ChromeDriver driver;

    public ChromeDriver getDriver() {
        return driver;
    }

    private static final BrowserHandler instance = new BrowserHandler();

    private static String OS;

    private BrowserHandler() {

        OS = System.getProperty("os.name");

        ChromeDriverManager.chromedriver().setup();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1200x600");
        options.addArguments("blink-settings=imagesEnabled=false");

        if (Linux()) {
            options.setBinary("/usr/bin/google-chrome");
        } else if (Windows()) {
            options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        } else if (Mac()) {
            options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        }

        driver = new ChromeDriver(options);
    }

    public static BrowserHandler getInstance() {
        return instance;
    }
}
