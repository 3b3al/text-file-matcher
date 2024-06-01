package com.textfilematcher.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.textfilematcher.execption.ApiResponse;
import com.textfilematcher.service.FilesMatcherService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
@Tag( name  = "Files Matcher Controller")
@SecurityRequirement(name = "Authorization")
public class FilesMatcherController {

    private final FilesMatcherService filesMatcherService;

    @PostMapping(value = "/compare" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<Map<String ,String>> compareFiles( @RequestParam String directoryPath,
            @RequestPart(required = true ) MultipartFile file ) {
        
        return ApiResponse.ok(filesMatcherService.compareFiles(directoryPath, file));
    }
    
}
