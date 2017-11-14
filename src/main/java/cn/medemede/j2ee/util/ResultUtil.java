package cn.medemede.j2ee.util;


import cn.medemede.j2ee.model.Result;

public class ResultUtil {

    public static Result<Object> success(Object o) {

        Result<Object> result = new Result<>();
        result.setCode(0);
        result.setMsg("success");
        result.setData(o);

        return result;
    }

    public static Result error(Integer code, String msg) {

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;

    }
}
