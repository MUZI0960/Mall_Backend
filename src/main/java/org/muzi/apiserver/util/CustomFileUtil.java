package org.muzi.apiserver.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${org.muzi.upload.path}")
    private String uploadPath;

    @PostConstruct
    public void init() {
        // 폴더 생성
        File tempFolder = new File(uploadPath);
        if(!tempFolder.exists()){
            tempFolder.mkdirs();
        }

        uploadPath = tempFolder.getAbsolutePath();
        log.info("-----------------------------");
        log.info(uploadPath);

    }

    public List<String> saveFile(List<MultipartFile> files) throws RuntimeException{

        if(files == null || files.size() == 0){
            return null;
        }

        List<String> uploadNames = new ArrayList<>();

        for(MultipartFile file : files){
            String savedName = UUID.randomUUID()+"_"+file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, savedName);

            try {
                Files.copy(file.getInputStream(), savePath);
                uploadNames.add(savedName.toString());
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }

        return uploadNames;
    }

}
