package pl.klubstrzelecki.serwer_klub_strzelecki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ApiController {

    @GetMapping("/")
    public String goHome() {
        return "This is publickly accesible withing needing authentication";
    }

}
