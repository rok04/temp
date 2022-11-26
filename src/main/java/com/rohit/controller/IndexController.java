package com.rohit.controller;

import com.rohit.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @GetMapping("/")
    public String list() {
        System.out.println("LIST");
        return "index";
    }
}
