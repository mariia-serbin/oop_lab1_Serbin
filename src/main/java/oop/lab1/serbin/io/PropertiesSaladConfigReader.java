package oop.lab1.serbin.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import oop.lab1.serbin.service.SaladConfig;

/**
 * Reads salad configuration from a {@link Properties} file.
 */
public class PropertiesSaladConfigReader implements SaladConfigReader {

    private static final String KEY_NAME = "salad.name";
    private static final String KEY_SORT = "salad.sort.parameter";
    private static final String KEY_MIN = "salad.calorie.range.min";
    private static final String KEY_MAX = "salad.calorie.range.max";

    @Override
    public SaladConfig read(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        String name = properties.getProperty(KEY_NAME);
        String sort = properties.getProperty(KEY_SORT);
        double min = Double.parseDouble(properties.getProperty(KEY_MIN));
        double max = Double.parseDouble(properties.getProperty(KEY_MAX));
        return new SaladConfig(name, sort, min, max);
    }
}