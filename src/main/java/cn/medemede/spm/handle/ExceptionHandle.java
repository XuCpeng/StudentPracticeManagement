package cn.medemede.spm.handle;


import cn.medemede.spm.exception.UnifiedRuntimeException;
import cn.medemede.spm.model.Result;
import cn.medemede.spm.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常拦截
 *
 * @author Saber
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        if (e instanceof UnifiedRuntimeException) {
            return ResultUtil.error(((UnifiedRuntimeException) e).getCode(), e.getMessage());
        } else {
            logger.error("【系统异常】：{}", e);
            return ResultUtil.error(-1, "系统错误");
        }
    }

}
