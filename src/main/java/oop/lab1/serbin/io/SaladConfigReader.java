package oop.lab1.serbin.io;

import java.io.IOException;
import java.io.InputStream;

import oop.lab1.serbin.service.SaladConfig;

public interface SaladConfigReader {

    SaladConfig read(InputStream inputStream) throws IOException;
}