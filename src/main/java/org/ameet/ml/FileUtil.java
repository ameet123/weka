package org.ameet.ml;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by ameet.chaubal on 6/30/2017.
 */
public class FileUtil {
    public static void writeFile(String absolutePath, String content) {
        try {
            Files.write(Paths.get(absolutePath), content.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("ERR: writing to file " + e.getMessage());
        }
    }

    public static List<String> getPathFileContent(String file, boolean isRemoveHeader) {
        URL url = Resources.getResource(file);
        List<String> text = null;
        try {
            text = Resources.readLines(url, Charsets.UTF_8);
        } catch (IOException e) {
            System.out.println("ERR: reading from classpath file " + e.getMessage());
            return null;
        }
        if (isRemoveHeader) {
            return text.subList(1, text.size());
        } else {
            return text;
        }
    }
}
