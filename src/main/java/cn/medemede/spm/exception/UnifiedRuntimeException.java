package cn.medemede.spm.exception;

import cn.medemede.spm.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 统一自定义异常
 *
 * @author Saber
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnifiedRuntimeException extends RuntimeException {

    private final Integer code;

    public UnifiedRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public UnifiedRuntimeException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
