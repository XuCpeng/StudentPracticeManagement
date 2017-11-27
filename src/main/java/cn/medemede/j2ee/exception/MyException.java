package cn.medemede.j2ee.exception;

import cn.medemede.j2ee.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一自定义异常
 * @author Saber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MyException extends RuntimeException {

    private Integer code;

    public MyException(Integer code, String message){
        super(message);
        this.code=code;
    }

    public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
