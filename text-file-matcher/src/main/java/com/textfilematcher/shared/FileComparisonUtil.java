package com.textfilematcher.shared;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

public class FileComparisonUtil {

        public static Set<String> getWords(MultipartFile file) throws IOException {
        String content = new String(file.getBytes());
        return new HashSet<>(Arrays.asList(content.split("\\W+")));
    }

    public static Set<String> getWords(Path filePath) throws IOException {
        String content = new String(Files.readAllBytes(filePath));
        return new HashSet<>(Arrays.asList(content.split("\\W+")));
    }

    public static double jaccardSimilarity(Set<String> set1, Set<String> set2) {
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return ((double) intersection.size() / union.size())*100;
    }
}
