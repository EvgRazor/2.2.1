package com.javanostress;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/") // то, что ввел юзер
    public String sayHello () {
        return "Hello.html"; // на на какую страницу перекинет метод при введенной ссылке
    }

}
