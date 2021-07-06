package cn.medemede.spm.service;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;

/**
 * 密码处理
 *
 * @author Saber
 */
@Service
public class PasswordService {

    private final int hashIterations = 1024;

    public String resetPass(String stuId) {

        //得到用户账号的加密密码
        ByteSource credentialsSalt = ByteSource.Util.bytes(stuId);

        return new Md5Hash(stuId, credentialsSalt, hashIterations).toBase64();
    }


    public String UpdatePass(String stuId, String password) {

        ByteSource credentialsSalt = ByteSource.Util.bytes(stuId);

        return new Md5Hash(password, credentialsSalt, hashIterations).toBase64();

    }
}
