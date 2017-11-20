package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.*;
import cn.medemede.j2ee.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SignUpController {

    @Resource
    private UserService userService;

    @PostMapping("/signup")
    public Result doSignup(@RequestParam("stuId") String stuId,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("roleName") String roleName){

        User user=new User();
        user.setStuId(stuId);
        user.setPwd(pwd);

        JUserRole2 jUserRole2=new JUserRole2();
        jUserRole2.setStuId(stuId);
        jUserRole2.setRoleName(roleName);

        return userService.addUser(user,jUserRole2);
    }

}
