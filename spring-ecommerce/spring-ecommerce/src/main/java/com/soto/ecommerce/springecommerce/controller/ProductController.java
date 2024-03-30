package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.model.User;
import com.soto.ecommerce.springecommerce.service.ProductService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products", productService.findAll());
        return "products/show";
    }
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
    @PostMapping("/save")
    public String save(Product product){
        LOGGER.info("Este es el objeto producto {}",product);
        User u = new User(1, "", "", "", "", "", "", "" );
        product.setUser(u);
        productService.save(product);
        return "redirect:/products";
    }

}
