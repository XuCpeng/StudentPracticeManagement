package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.enums.ResultEnum;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.model.User;
import cn.medemede.j2ee.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SignUpController {

    @Resource
    private UserRepository userRepository;

    @PostMapping("/signup")
    public Result doSignup(@RequestParam("stuId") String stuId,
                           @RequestParam("pwd") String pwd){
        User user=new User();
        user.setStuId(stuId);
        user.setPwd(pwd);

        Result result=new Result();

        if (userRepository.findOne(stuId)!=null)
        {
            result.setResultEnum(ResultEnum.USER_HEAD);
        }else {
            userRepository.save(user);
            result.setResultEnum(ResultEnum.SAVEUSER_SUCCESS);
        }

        return result;
    }

}
