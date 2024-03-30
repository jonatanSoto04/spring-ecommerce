package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.DetailOrder;
import com.soto.ecommerce.springecommerce.model.Order;
import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private ProductService productService;
    //Para almacenar los detalles de la orden
    List<DetailOrder> detail = new ArrayList<DetailOrder>();
    //datos de la orden
    Order order = new Order();
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products", productService.findAll());
        return "user/home";
    }
    @GetMapping("producthome/{id}")
    public String productHome(@PathVariable Integer id, Model model){
        log.info("ID producto enviado como parametro {}", id);
        Product product = new Product();
        Optional<Product> productOptional = productService.get(id);
        product = productOptional.get();
        model.addAttribute("product", product);
        return "user/producthome";
    }
    @PostMapping("cart")
    public String addCart(@RequestParam Integer id, @RequestParam Integer amount){
        DetailOrder detailOrder = new DetailOrder();
        Product product = new Product();
        double sumTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("Producto añaido: {}", optionalProduct.get());
        log.info("Cantidad: {}", amount);


        return "user/trolley";
    }



}
