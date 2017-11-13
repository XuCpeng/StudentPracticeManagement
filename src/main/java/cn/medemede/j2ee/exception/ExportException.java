package cn.medemede.j2ee.exception;

import cn.medemede.j2ee.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExportException extends RuntimeException {

    private Integer code;

    public ExportException(Integer code,String message){
        super(message);
        this.code=code;
    }

    public ExportException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
