package com.soto.ecommerce.springecommerce.controller;

import com.soto.ecommerce.springecommerce.model.User;
import com.soto.ecommerce.springecommerce.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.PublicKey;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;
    // /user/registro
    @GetMapping("/registro")
    public String create(){
        return "user/register";
    }

    @PostMapping("/save")
    public String save(User user) {
        logger.info("Usuario registro {}", user);
        user.setType("USER");
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(){
        return "user/login";
    }
    @PostMapping("/acceder")
    public String acess(User user, HttpSession session){
        logger.info("Accesos: {}",user);
        Optional<User> usuario = userService.findByEmail(user.getEmail());
        //logger.info("Usuario de db: {}", usuario.get());
        if(usuario.isPresent()) {
            session.setAttribute("id usuario", usuario.get().getId());
            if (usuario.get().getType().equals("ADMIN")) {
                return "redirect:/administrator";
            } else {
                return "redirect:/";
            }
        }else{
                logger.info("Usuario no existe");
        }

        return "redirect:/";
    }

}
