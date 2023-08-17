package com.itblee.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public final class FileUtils {

    private FileUtils() {
        throw new AssertionError();
    }

    private static final Path ROOT = Paths.get("C://home/office");

    public static String getFileExtension(String source) {
        if (StringUtils.isBlank(source))
            return "";
        return "." + source.substring("data:image/".length(), source.indexOf(";base64"));
    }

    public static void save(String dir, String fileName, String source) {
        if (StringUtils.isBlank(source))
            return;
        try {
            Path path = ROOT.resolve(dir);
            if (Files.notExists(path))
                Files.createDirectories(path);
            byte[] bytes = Base64.getDecoder().decode(source.split(",")[1]);
            Files.write(path.resolve(fileName), bytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static byte[] load(String dir) {
        try {
            return Files.readAllBytes(ROOT.resolve(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteIfExists(String dir) {
        if (StringUtils.isBlank(dir))
            return;
        try {
            Files.deleteIfExists(ROOT.resolve(dir));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
