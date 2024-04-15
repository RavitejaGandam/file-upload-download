package com.ravi.fileuploaddownload.service;

import com.ravi.fileuploaddownload.entity.ImageData;
import com.ravi.fileuploaddownload.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;


    public String uploadImage(MultipartFile multipartFile) throws IOException {
        ImageData imageData = imageRepository.save(ImageData.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imageData(multipartFile.getBytes()) // Store raw image data
                .build());

        return "File uploaded successfully: " + multipartFile.getOriginalFilename();

    }
    
    public byte[] downloadImage(String fileName){
        Optional<ImageData> dbImage = imageRepository.findByName(fileName);
        return dbImage.map(ImageData::getImageData).orElse(null);
    }
}
