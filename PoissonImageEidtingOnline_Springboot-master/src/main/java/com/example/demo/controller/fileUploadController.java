package com.example.demo.controller;
import com.example.demo.Algorithms.image;
import com.example.demo.Algorithms.imagecoder;
import com.example.demo.document.Images;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class fileUploadController {
    String uploadDirectory = System.getProperty("user.dir")+ "/static";
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload( @RequestParam("background") MultipartFile background, @RequestParam("insertimage") MultipartFile insertimage, @RequestParam("mask") MultipartFile mask,HttpSession session){
        Path fileNameAndPath = Paths.get(uploadDirectory,"background.jpg");
        try{
            Files.write(fileNameAndPath, background.getBytes());
        } catch(IOException e){
            e.printStackTrace();
        }
        fileNameAndPath = Paths.get(uploadDirectory,"insertimage.jpg");
        try{
            Files.write(fileNameAndPath, insertimage.getBytes());
        } catch(IOException e){
            e.printStackTrace();
        }
        fileNameAndPath = Paths.get(uploadDirectory,"mask.jpg");
        try{
            Files.write(fileNameAndPath, mask.getBytes());
        } catch(IOException e){
            e.printStackTrace();
        }
        return "redirect:/status";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(HttpSession session){
        if(session.getAttribute("user") == null){
            return "redirect:/login";
        }
        else{
            return "uploadView";
        }
    }

}
