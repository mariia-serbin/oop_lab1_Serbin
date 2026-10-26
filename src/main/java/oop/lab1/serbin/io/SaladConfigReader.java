package oop.lab1.serbin.io;

import java.io.IOException;
import java.io.InputStream;

import oop.lab1.serbin.service.SaladConfig;

/**
 * Reads salad configuration from an input source.
 */
public interface SaladConfigReader {

    /**
     * Reads the configuration from the given stream.
     *
     * @param inputStream the source stream
     * @return the salad configuration
     * @throws IOException if the stream cannot be read
     */
    SaladConfig read(InputStream inputStream) throws IOException;
}