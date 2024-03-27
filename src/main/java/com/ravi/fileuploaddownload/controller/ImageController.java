package com.ravi.fileuploaddownload.controller;

import com.ravi.fileuploaddownload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class ImageController {
    @Autowired
    ImageService imageService;

//    public ImageController(ImageService imageService) {
//        this.imageService = imageService;
//    }
    @PostMapping("/upload")
    public ResponseEntity<?> fileUpload(@RequestParam MultipartFile multipartFile) throws IOException {
        String imageupload = imageService.uploadImage(multipartFile);
        ResponseEntity<String> body = ResponseEntity.status(HttpStatus.OK).body(imageupload);
        return body;
    }



    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> fileDownload(@PathVariable String fileName){
        byte[] imageInBytes = imageService.downloadImage(fileName);
        return  ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG).body(imageInBytes);

    }




}
