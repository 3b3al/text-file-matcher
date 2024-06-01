package com.textfilematcher.service;

import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface FilesMatcherService {

    Map<String ,String> compareFiles(String directoryPath ,MultipartFile file) ;
}
