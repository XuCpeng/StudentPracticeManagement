package cn.medemede.j2ee.service;

import cn.medemede.j2ee.enums.ResultEnum;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.model.User;
import cn.medemede.j2ee.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordService passwordService;

    public Result addUser(User user){

        Result result=new Result();
        if (userRepository.findOne(user.getStuId())!=null)
        {
            result.setResultEnum(ResultEnum.USER_HEAD);
        }else {
            user.setPwd(passwordService.UpdatePass(user.getStuId(),user.getPwd()));
            userRepository.save(user);
            result.setResultEnum(ResultEnum.SAVEUSER_SUCCESS);
        }

        return result;
    }
}
