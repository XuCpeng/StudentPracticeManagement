package cn.medemede.j2ee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/signin")
    public String signIn(){
        String farword="sign-in";

        return farword;
    }

    @RequestMapping("signup")
    public String signUp(){
        String farword="sign-in";

        return farword;
    }
}
