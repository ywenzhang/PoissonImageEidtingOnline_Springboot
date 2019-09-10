package com.example.demo.services;

import com.example.demo.document.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ImagesRepository;


@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImagesRepository imagesRepository;
    @Override
    public Boolean saveImage(Images image){
        imagesRepository.save(image);
        return true;
    }
    @Override
    public Page<Images> listImages(int page){
        return imagesRepository.findAll(new PageRequest(page,8));
    }

}