package com.example.demo.controller;
import com.example.demo.Algorithms.image;
import com.example.demo.Algorithms.imagecoder;

import com.example.demo.document.Images;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

@Controller
public class UploadStatusController {
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() throws IOException {
        imagecoder.MaskDisplay();
        return "uploadStatusView";
    }

    @RequestMapping(value = "/status", method = RequestMethod.POST)
    public String status(Model model, @RequestParam String x, @RequestParam String y, @RequestParam(defaultValue = "false")  String GaussianSeidel, @RequestParam(defaultValue = "false")  String Multigrid,  HttpSession session){
        String uploadDirectory = System.getProperty("user.dir")+ "/static";
        String username = (String) session.getAttribute("user");
        int xI = (int) Double.parseDouble(x);
        int yI = (int) Double.parseDouble(y);
        System.out.println(Multigrid);
        System.out.println(GaussianSeidel);
        HashMap<String, Long> times = new HashMap<>();
        if(Multigrid.equals("true")) {
            times.put("Multigrid",image.Multigrid(xI, yI));
        }
        if(GaussianSeidel.equals("true")) {
            times.put("Gaussian-Seidel",image.GaussianSeidel(xI, yI));
        }
        String beforeS = imagecoder.Encoder(new File(uploadDirectory+"/before.jpg"));
        String outputS = imagecoder.Encoder(new File(uploadDirectory+"/output.jpg"));
        Images image = new Images(username, beforeS, outputS, times);
        model.addAttribute("image",image);
        session.setAttribute("image",image);
        return "save";
    }
}
