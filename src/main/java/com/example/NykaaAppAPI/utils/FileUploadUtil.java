package com.example.NykaaAppAPI.utils;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile, ResourceLoader resourceLoader)
            throws IOException {
        Path uploadPath = Paths.get("Files-Upload");

//        Resource res = resourceLoader.getResource("classpath:application.properties");
//
//        Path uploadPath = Paths.get(res.getFilename() + "/upload");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileCode;
    }

    public static String getFilePath(String path) {
        Path uploadPath = Paths.get("Files-Upload");
        File file = uploadPath.toFile();

        return file.getAbsolutePath();
    }
}
