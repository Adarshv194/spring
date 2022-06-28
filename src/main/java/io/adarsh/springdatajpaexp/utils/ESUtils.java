package io.adarsh.springdatajpaexp.utils;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ESUtils {

    public static String loadAsString(final String path) {
        try {
            File file = new ClassPathResource(path).getFile();
            byte[] bytes = Files.readAllBytes(file.toPath());
            return new String(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
