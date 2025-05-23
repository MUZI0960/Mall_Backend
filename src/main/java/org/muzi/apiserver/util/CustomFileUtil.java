package org.muzi.apiserver.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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

    
    @PostConstruct      // 생성자 대신 자주 사용
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

    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException{

        if(files == null || files.size() == 0){
            return null;
        }

        List<String> uploadNames = new ArrayList<>();

        for(MultipartFile file : files){
            String savedName = UUID.randomUUID()+"_"+file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, savedName);

            try {
                Files.copy(file.getInputStream(), savePath);    // 원본파일 업로드

                // 이미지인 경우에는 썸네일 생성
                String contentType = file.getContentType(); // Mime Type
                if(contentType != null || contentType.startsWith("image")){
                    Path thumnailPath = Paths.get(uploadPath, "s_" + savedName);

                    Thumbnails.of(savePath.toFile()).size(200,200).toFile(thumnailPath.toFile());


                }


                uploadNames.add(savedName.toString());
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        }// end for

        return uploadNames;
    }

    public ResponseEntity<Resource> getFile(String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        if(!resource.isReadable()){
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");
        }

        HttpHeaders headers = new HttpHeaders();

        try {
            headers.add("content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(headers).body(resource);

    }

    public void deleteFiles(List<String> fileNames){
        if(fileNames == null || fileNames.size() == 0){
            return;
        }

        fileNames.forEach(fileName -> {
            // 썸네일 삭제
            String thumnailFileName = "s_" + fileName;
            Path thumbnailPath = Paths.get(uploadPath, thumnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            }catch (IOException e){
                throw new RuntimeException(e);
            }

        });
    }

}
