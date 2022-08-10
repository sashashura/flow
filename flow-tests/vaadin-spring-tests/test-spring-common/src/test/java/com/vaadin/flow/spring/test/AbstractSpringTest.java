package com.vaadin.flow.spring.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;

import com.vaadin.flow.testutil.ChromeBrowserTest;

public abstract class AbstractSpringTest extends ChromeBrowserTest {

    @Override
    protected String getTestURL(String... parameters) {
        return getTestURL(getRootURL(), getContextPath() + getTestPath(),
                parameters);
    }

    protected String getContextRootURL() {
        return getRootURL() + getContextPath();
    }

    protected String getContextPath() {
        Properties p = new Properties();
        try {
            InputStream res = getClass()
                    .getResourceAsStream("/application.properties");
            if (res != null) {
                p.load(res);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String contextPath = p.getProperty("server.servlet.contextPath");
        if (contextPath != null) {
            return contextPath;
        } else {
            return "";
        }
    }

    @After
    public void dumpBrowserConsoleLogs() {
        Logs logs = driver.manage().logs();
        System.out.println(logs.getAvailableLogTypes().stream()
                .flatMap(logType -> logs.get(logType).getAll().stream()
                        .map(LogEntry::toString))
                .collect(Collectors.joining("\n",
                        "===============================  DUMP Browser logs:\n",
                        "\n\n")));
    }

}
