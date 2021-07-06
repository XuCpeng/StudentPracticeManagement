package cn.medemede.spm.model;

import cn.medemede.spm.enums.ResultEnum;

/**
 * @author xcp
 */
@lombok.Data
public class Result {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    public Result() {
    }

    public Result(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public void setResultEnum(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMessage();
    }

}
