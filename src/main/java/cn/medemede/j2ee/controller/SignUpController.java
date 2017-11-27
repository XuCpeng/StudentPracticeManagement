package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.*;
import cn.medemede.j2ee.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Saber
 */
@RestController
public class SignUpController {

    @Resource
    private UserService userService;

    /**
     * 注册
     * @param stuId
     * @param pwd
     * @param roleName
     * @return
     */
    @PostMapping("/signup")
    public Result doSignup(@RequestParam("stuId") String stuId,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("roleName") String roleName,
                           @RequestParam("stuName") String stuName){

        User user=new User();
        user.setStuId(stuId);
        user.setPwd(pwd);

        JUserRole2 jUserRole2=new JUserRole2();
        jUserRole2.setStuId(stuId);
        jUserRole2.setRoleName(roleName);

        AcProve acProve=new AcProve();
        acProve.setStuId(stuId);
        acProve.setStuName(stuName);

        return userService.addUser(user,jUserRole2,acProve);
    }

}
