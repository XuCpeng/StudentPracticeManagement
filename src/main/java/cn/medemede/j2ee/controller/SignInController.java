package cn.medemede.j2ee.controller;

import cn.medemede.j2ee.enums.ResultEnum;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.model.User;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@RestController
public class SignInController {

    private final
    DefaultKaptcha defaultKaptcha;

    @Autowired
    public SignInController(DefaultKaptcha defaultKaptcha) {
        this.defaultKaptcha = defaultKaptcha;
    }

    @PostMapping("/signin")
    public Result signIn(@RequestParam String stuId,
                         @RequestParam String pwd,
                         @RequestParam String checkcode,
                         HttpServletRequest request){
        Result result = new Result();
        HttpSession session = request.getSession();
        String trueCode = (String)session.getAttribute("checkcode");
        String inputCode=checkcode.toLowerCase();

        if (inputCode.equals(trueCode)){
            Subject currentUser = SecurityUtils.getSubject();
            //把用户名密码封装成token对象
            UsernamePasswordToken token = new UsernamePasswordToken(stuId, pwd);
            token.setRememberMe(false);
            try {
                currentUser.login(token);
                User user=new User();
                user.setStuId(stuId);

                if(currentUser.hasRole("student")){
                    result.setResultEnum(ResultEnum.STUDENT_LOGIN);
                }else {
                    result.setResultEnum(ResultEnum.ADMAIN_LOGIN);
                }
            } catch (ExcessiveAttemptsException e){
                result.setResultEnum(ResultEnum.MORE_PWDERROR_LUCK);

            } catch (AuthenticationException e) {
               result.setResultEnum(ResultEnum.LOG_FILED);
            }
        }else{
            result.setResultEnum(ResultEnum.CHECKCODE_ERROR);
        }

        return result;
    }

    @RequestMapping("/checkcode")
    public void getCheckCode(HttpServletResponse response, HttpServletRequest request) throws IOException {

        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            request.getSession().setAttribute("checkcode", createText.toLowerCase());
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();

    }
}
