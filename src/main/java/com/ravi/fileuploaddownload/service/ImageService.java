package com.ravi.fileuploaddownload.service;

import com.ravi.fileuploaddownload.entity.ImageData;
import com.ravi.fileuploaddownload.repository.ImageRepository;
import com.ravi.fileuploaddownload.utility.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
    @Autowired
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public String uploadImage(MultipartFile multipartFile) throws IOException {
        ImageData result = imageRepository.save(ImageData.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .imageData(ImageUtils.compressImage(multipartFile.getBytes())).build());

        return  (result!=null) ?  "Hurray ! Your File/Image Successfully Uploaded" : "Oops... Image/File not Saved Try again..";

    }

    public byte[] downloadImage(String fileName){
        Optional<ImageData> image = imageRepository.findByName(fileName);
        byte [] imageFile=ImageUtils.decompressImage(image.get().getImageData());
        return imageFile;
    }
}
