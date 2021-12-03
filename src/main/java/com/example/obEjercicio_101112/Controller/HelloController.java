package com.example.obEjercicio_101112.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${app.varexample}")
    String message;

    @GetMapping("/Saludo")  // --> http://localhost:8081/Saludo
    public String Saludo(){
        return "Otro saludito por aqui.";
    }

}
