package cn.medemede.j2ee.model;

import lombok.Data;

@Data
public class Result<T> {

    //错误码
    private Integer code;

    //提示信息
    private String msg;

    //数据
    private T data;

    public Result(){}

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
