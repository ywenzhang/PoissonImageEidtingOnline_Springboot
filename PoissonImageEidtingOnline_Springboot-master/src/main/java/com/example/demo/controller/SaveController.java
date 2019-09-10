package com.example.demo.controller;

import com.example.demo.document.Images;
import com.example.demo.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
public class SaveController {
    @Autowired
    private ImageService imageService;
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save() throws IOException {
        return "save";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpSession session, @RequestParam(defaultValue = "") String imagename){
        Images image = (Images)session.getAttribute("image");
        System.out.println(image);
        image.setImagename(imagename);
        imageService.saveImage(image);
        return "saveSuccessful";
    }
}
