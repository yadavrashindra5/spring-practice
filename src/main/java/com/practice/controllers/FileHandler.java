package com.practice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileHandler {

    Logger logger= LoggerFactory.getLogger(FileHandler.class);
    @PostMapping
    public void fileHandler(@RequestParam(name = "file")MultipartFile file) throws IOException {
//        /home/rashi/Desktop/Program/MasterSpringBoot/Spring Practice
        File imageDirectory=new File("/home/rashi/Desktop/Program/MasterSpringBoot/practice/image");
        if(!imageDirectory.exists()){
            imageDirectory.mkdir();
        }
        String originalName=file.getOriginalFilename();
        String fileName= UUID.randomUUID().toString();
        String extension=originalName.substring(originalName.lastIndexOf("."));
        String fileNameWithExtension="/home/rashi/Desktop/Program/MasterSpringBoot/practice/image/"+fileName+extension;
        Path file1 = Files.createFile(Paths.get(fileNameWithExtension));
        logger.info("originalName,fileName,extension{}",file1);
    }
}
