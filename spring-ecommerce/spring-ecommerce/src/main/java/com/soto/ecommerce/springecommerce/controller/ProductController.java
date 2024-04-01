package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.Product;
import com.soto.ecommerce.springecommerce.model.User;
import com.soto.ecommerce.springecommerce.service.IUserService;
import com.soto.ecommerce.springecommerce.service.ProductService;
import com.soto.ecommerce.springecommerce.service.UploadFileService;
import com.soto.ecommerce.springecommerce.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private UploadFileService upload;
    @Autowired
    private IUserService userService;

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
    public String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto producto {}",product);
        User u = userService.findById(Integer.parseInt(session.getAttribute(("iduser")).toString())).get();
        product.setUser(u);
        //imagen
        if(product.getId()==null){ //Validacion de cuando se crea un producto
            String nameImagen = upload.saveImage(file);
            product.setImage(nameImagen);
        }

        productService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();

        LOGGER.info("Producto buscado: {}",product);
        model.addAttribute("product", product);
        return "products/edit";
    }
    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException {
        Product p = new Product();
        p = productService.get(product.getId()).get();
            if(file.isEmpty()){ //Editamos el producto pero no se cambia la imagen
                product.setImage(p.getImage());
            }else {//cuando se edita tbn la imagen
                //Eliminar cuando no sea la imagen por defecto
                if (!p.getImage().equals("default.jpg")){
                    upload.deleteImage(p.getImage());
                }
                String nombreImagen = upload.saveImage(file);
                product.setImage(nombreImagen);
            }
        product.setUser(p.getUser());
        productService.update(product);
        return "redirect:/products";

    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        Product p = new Product();
        p = productService.get(id).get();
        //Eliminar cuando no sea la imagen por defecto
        if (!p.getImage().equals("default.jpg")){
            upload.deleteImage(p.getImage());
        }

        productService.delete(id);
        return "redirect:/products";
    }
}
