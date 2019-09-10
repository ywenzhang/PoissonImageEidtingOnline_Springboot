package com.example.demo.controller;
import com.example.demo.document.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private com.example.demo.services.UserService userService;
    @GetMapping
    public String registrationPage(){
        return "register";
    }
    @PostMapping("/submit")
    public String registration(@RequestParam String username, @RequestParam String password,@RequestParam String password2, @RequestParam String email, RedirectAttributes attributes){
        if(!password.equals(password2)){
            attributes.addFlashAttribute("passowrdWrong","Conformed password doesn't match");
            return "redirect:/registration";
        }
        Users user = userService.checkUser(username,password);
        if(user != null){
            attributes.addFlashAttribute("usernameWrong","Duplicate Username");
            return "redirect:/registration";
        }else{
            Users newuser = new Users(username,email,password);
            userService.saveUser(newuser);
            newuser.setPassword(null);
            return "registrationSuccessful";
        }
    }

}
