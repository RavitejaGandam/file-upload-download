package com.ravi.fileuploaddownload.controller;

import com.ravi.fileuploaddownload.entity.ImageData;
import com.ravi.fileuploaddownload.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private ImageService imageService;
    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile multipartFile) throws IOException {
        String uploadImage = imageService.uploadImage(multipartFile);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable String fileName){
     byte[] imageData =   imageService.downloadImage(fileName);
     if(imageData!=null) {
         return ResponseEntity.status(HttpStatus.OK)
                 .contentType(MediaType.IMAGE_JPEG)
                 .body(imageData);
     }
     else {
         return ResponseEntity.notFound().build();
     }

    }

    @GetMapping("/getAll")
    public List<ImageData> getAllFiles(){
        return imageService.getAll();
    }
}
