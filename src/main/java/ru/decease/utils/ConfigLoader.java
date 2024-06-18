package ru.decease.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + CONFIG_FILE);
            }
            properties.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Error loading " + CONFIG_FILE, ex);
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("baseUrl");
    }

    public static String getUserName() {
        return properties.getProperty("userName");
    }

    public static String getPassword() {
        return properties.getProperty("password");
    }
    public static String chromeDriverPath() {
        return properties.getProperty("chromeDriverPath");
    }
    public static int countBookInNewProfile() {
        return Integer.parseInt(properties.getProperty("countBookInNewProfile"));
    }
    public static int countBookAdd() {
        return Integer.parseInt(properties.getProperty("countBookAdd"));
    }

    public static int expectedCountBook() {
        return Integer.parseInt(properties.getProperty("expectedCountBook"));
    }

    public static int countBookAddAndDelete() {
        return Integer.parseInt(properties.getProperty("countBookAddAndDelete"));
    }

    public static int expectedCountBookAddAndDelete() {
        return Integer.parseInt(properties.getProperty("expectedCountBookAddAndDelete"));
    }
}
