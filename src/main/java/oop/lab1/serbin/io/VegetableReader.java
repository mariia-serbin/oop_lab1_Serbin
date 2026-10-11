package oop.lab1.serbin.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import oop.lab1.serbin.model.Vegetable;

public interface VegetableReader {

    List<Vegetable> read(InputStream inputStream) throws IOException;
}