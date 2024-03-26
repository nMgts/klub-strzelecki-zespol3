package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String homeController() {
        return "abc";
    }


}
