package com.example.metrics;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads default metric keys from a properties file into the singleton registry.
 *
 * Solution that I have implemented : replaced `new MetricsRegistry()` with `MetricsRegistry.getInstance()`
 * so that MetricsLoader always operates on the one-and-only registry instance
 * shared by the entire application.
 */
public class MetricsLoader {

    public MetricsRegistry loadFromFile(String path) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(path)) {
            props.load(fis);
        }

        // Use the singleton – never create a new instance directly.
        MetricsRegistry registry = MetricsRegistry.getInstance();

        for (String key : props.stringPropertyNames()) {
            String raw = props.getProperty(key, "0").trim();
            long v;
            try {
                v = Long.parseLong(raw);
            } catch (NumberFormatException e) {
                v = 0L;
            }
            registry.setCount(key, v);
        }
        return registry;
    }
}
