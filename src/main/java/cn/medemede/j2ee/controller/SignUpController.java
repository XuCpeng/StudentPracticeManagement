package cn.medemede.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {

    @RequestMapping("/signup")
    public String signUp(){

        return "sign-up";
    }

}
