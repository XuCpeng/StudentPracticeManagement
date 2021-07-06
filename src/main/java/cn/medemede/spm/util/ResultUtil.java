package cn.medemede.spm.util;


import cn.medemede.spm.model.Result;
import cn.medemede.spm.model.ResultWithData;

/**
 * @author Saber
 */
public class ResultUtil {

    public static ResultWithData<Object> success(Object o) {

        return new ResultWithData<>(0, "success", o);
    }

    public static Result error(Integer code, String msg) {

        return new Result(code, msg);

    }
}
