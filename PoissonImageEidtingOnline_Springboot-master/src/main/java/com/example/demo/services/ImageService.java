package com.example.demo.services;

import com.example.demo.document.Images;
import org.springframework.data.domain.Page;

public interface ImageService {
    Page<Images> listImages(int page);
    Boolean saveImage(Images image);
}
