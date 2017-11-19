package cn.medemede.j2ee.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private int hashIterations = 1024;

    public String ResetPass(String stuId) {

        //得到用户账号的加密密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(stuId);

        return new Md5Hash(stuId, credentialsSalt, hashIterations).toBase64();
    }



    public String UpdatePass(String stuId, String password) {

        ByteSource credentialsSalt = ByteSource.Util.bytes(stuId);

        return new Md5Hash(password, credentialsSalt, hashIterations).toBase64();

    }
}
