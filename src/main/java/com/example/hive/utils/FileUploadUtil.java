package com.example.hive.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploadUtil {
    public static String saveFile(String uploadDir, String fileName,
                                  MultipartFile multipartFile) throws IOException {
        File file = new File(uploadDir);

        if (!file.exists()) {
            file.mkdir();
        }

        String finalFileName = UUID.randomUUID().toString() + "_" + fileName;
        String filePath = uploadDir + "/" + finalFileName;
        multipartFile.transferTo(new File(filePath));

        return finalFileName;
    }
}