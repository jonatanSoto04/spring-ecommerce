package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.DetailOrder;
import com.soto.ecommerce.springecommerce.model.Order;
import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.model.User;
import com.soto.ecommerce.springecommerce.service.IUserService;
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
    @Autowired
    private IUserService userService;
    //Para almacenar los detalles de la orden
    List<DetailOrder> details = new ArrayList<DetailOrder>();
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
    public String addCart(@RequestParam Integer id, @RequestParam Integer amount, Model model){
        DetailOrder detailOrder = new DetailOrder();
        Product product = new Product();
        double sumTotal = 0;

        Optional<Product> optionalProduct = productService.get(id);
        log.info("Producto añaido: {}", optionalProduct.get());
        log.info("Cantidad: {}", amount);
        product=optionalProduct.get();

        detailOrder.setAmount(amount);
        detailOrder.setPrice(product.getPrice());
        detailOrder.setName(product.getName());
        detailOrder.setTotal(product.getPrice()*amount);
        detailOrder.setProduct(product);

        //Validaciono para que el producto no se añada dos veces
        Integer idPrduct = product.getId();
        boolean joined = details.stream().anyMatch(p -> p.getProduct().getId() == idPrduct);

        if(!joined){
            details.add(detailOrder);
        }

        sumTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal(sumTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/trolley";
    }

    //Qutar un producto del carrito
    @GetMapping("/delete/cart/{id}")
    public String deleteProductcart(@PathVariable Integer id, Model model){
        //Lista nueva de productos
        List<DetailOrder> newOrders = new ArrayList<DetailOrder>();
        for(DetailOrder detailOrder: details){
            if(detailOrder.getProduct().getId()!=id){
                newOrders.add(detailOrder);
            }
        }
        //Nueva lista con los productos restantes
        details= newOrders;
        double sumTotal = 0;

        sumTotal = details.stream().mapToDouble(dt->dt.getTotal()).sum();
        order.setTotal(sumTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);

        return "user/trolley";
    }

    @GetMapping("/getCart")
    public String getCart(Model model){
        double sumTotal = 0;
        order.setTotal(sumTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        return "/user/trolley";
    }

    @GetMapping("/order")
    public String order(Model model){
        User user = userService.findById(1).get();
        double sumTotal = 0;
        order.setTotal(sumTotal);
        model.addAttribute("cart", details);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "user/ordersummary";
    }

}
