package com.textfilematcher.service.impl;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.FilerException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.textfilematcher.service.FilesMatcherService;
import com.textfilematcher.shared.FileComparisonUtil;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilesMatcherServiceImpl implements FilesMatcherService{

    @Override
    public Map<String, String> compareFiles(String directoryPath, MultipartFile file) {
        
        Map<String, String> similarityScores = new HashMap<>();

        try {
            // Get words from the uploaded file
            Set<String> uploadedFileWords = FileComparisonUtil.getWords(file);
            if (uploadedFileWords.size() > 10000000 ) {
                throw new ValidationException("File can not contain more than 10M word.");
            }

            Path dirPath = Paths.get(directoryPath);
            // Get the list of files in the directory and check the count
            long fileCount = Files.list(dirPath).filter(Files::isRegularFile).count();
            if (fileCount > 20) {
                throw new ValidationException("Directory exceeds the maximum file limit of 20 files.");
            }
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
                for (Path entry : stream) {
                    if (Files.isRegularFile(entry)) {
                        Set<String> directoryFileWords = FileComparisonUtil.getWords(entry);
                        if (directoryFileWords.size() > 10000000) {
                            throw new ValidationException("File can not contain more than 10M word.");
                        }

                        double similarityScore = FileComparisonUtil.jaccardSimilarity(uploadedFileWords, directoryFileWords);
                        String formattedScore = String.format("%.0f%%", similarityScore);
                        similarityScores.put(entry.getFileName().toString(), formattedScore);
                    }
                }
            }

            return similarityScores;

        } catch (IOException e) {
            log.error(directoryPath, e);
            throw new ValidationException(e.getMessage());
        }
    }
    
   
}
