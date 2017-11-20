package cn.medemede.j2ee.handle;


import cn.medemede.j2ee.exception.MyException;
import cn.medemede.j2ee.model.Result;
import cn.medemede.j2ee.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截
 */
@ControllerAdvice
public class ExceptionHandle {

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        if (e instanceof MyException) {
            return ResultUtil.error(((MyException) e).getCode(), e.getMessage());
        } else {
            logger.error("【系统异常】：{}", e);
            return ResultUtil.error(-1, "系统错误");
        }
    }

}
