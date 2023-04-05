package com.company.holidaybackend.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/welcome")
@CrossOrigin
public class WelcomeController {

    @GetMapping()
    public String welcome(){
        return "Welcome to the project";
    }
}
