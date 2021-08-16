package com.geekbrains.ru.springmvcdemo.controller;

import com.geekbrains.ru.springmvcdemo.domain.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/main")
public class MainController {

    private int portNumber;

    public MainController(@Value("${server.port}") int portNumber) {
        this.portNumber = portNumber;
    }

    @GetMapping("/alpha")
    @ResponseBody
    private String getAlpha(){
        return "Alpha";
    }

    @PostMapping("/beta")
    @ResponseBody
    private String postAlpha(@RequestBody Product product){
        return product.toString();
    }
}
