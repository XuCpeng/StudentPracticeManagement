package cn.medemede.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index(){
        return "sign-in";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "sign-up2";
    }

}
