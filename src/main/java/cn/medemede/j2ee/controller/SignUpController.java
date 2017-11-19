package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.model.Role;
import cn.medemede.j2ee.model.User;
import cn.medemede.j2ee.repository.RoleRepository;
import cn.medemede.j2ee.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RestController
public class SignUpController {

    @Resource
    private UserService userService;

    @Resource
    private RoleRepository roleRepository;

    @PostMapping("/signup")
    public Result doSignup(@RequestParam("stuId") String stuId,
                           @RequestParam("pwd") String pwd,
                           @RequestParam("roleName") String roleName){
        User user=new User();
        Set<Role> roles=new HashSet<>();
        roles.add(roleRepository.findByRoleName(roleName));
        user.setStuId(stuId);
        user.setPwd(pwd);
        user.setUserRoleSet(roles);

        return userService.addUser(user);
    }

}
