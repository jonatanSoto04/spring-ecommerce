package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
    @Autowired
    private ProductService productService;
    @GetMapping("")
    public String home(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "administrator/home.html";
    }
}
