package com.example.NykaaAppAPI.service;

import com.example.NykaaAppAPI.model.File;
import com.example.NykaaAppAPI.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;


    public File uploadFile(File file){
        return fileRepository.save(file);
    }
}
