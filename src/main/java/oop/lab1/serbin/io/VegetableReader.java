package oop.lab1.serbin.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import oop.lab1.serbin.model.Vegetable;

/**
 * Reads vegetables from an input source.
 */
public interface VegetableReader {

    /**
     * Reads all vegetables from the given stream.
     *
     * @param inputStream the source stream
     * @return the list of vegetables
     * @throws IOException if the stream cannot be read
     */
    List<Vegetable> read(InputStream inputStream) throws IOException;
}