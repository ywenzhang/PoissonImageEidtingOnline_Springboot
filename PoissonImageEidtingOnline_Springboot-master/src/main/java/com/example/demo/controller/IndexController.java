package com.example.demo.controller;
import com.example.demo.Algorithms.image;
import com.example.demo.document.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.services.ImageService;
import com.example.demo.Algorithms.imagecoder;
import java.io.IOException;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue="0") int page) throws IOException {
        List<Images> imagesList = imageService.listImages(page).getContent();
        for(int i = 0; i<imagesList.size(); i++){
            imagecoder.Decoder(imagesList.get(i).getBefore(),i+"beforeImage");
            imagecoder.Decoder(imagesList.get(i).getOutput(),i+"outputImage");
        }
        model.addAttribute("data", imageService.listImages(page));
        model.addAttribute("imageNumbers",imagesList.size());
        return "index";
    }
}
