package cn.medemede.spm.model;

import lombok.EqualsAndHashCode;

/**
 * @author xcp
 */
@EqualsAndHashCode(callSuper = true)
@lombok.Data
public class ResultWithData<T> extends Result {

    /**
     * 数据
     */
    private T data;

    public ResultWithData() {

    }

    public ResultWithData(Integer code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }
}
