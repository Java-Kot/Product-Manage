package com.practice.controller;

import com.practice.model.Product;
import com.practice.service.ProductService;
import com.practice.service.ProductServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ProductServlet{
    private ProductService productService = new ProductServiceImpl();
    @GetMapping
    public ModelAndView showIndex(){
        ModelAndView modelAndView = new ModelAndView("index", "products", productService.findAll());
        return modelAndView;
    }
}