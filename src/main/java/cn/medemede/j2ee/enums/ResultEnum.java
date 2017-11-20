package cn.medemede.j2ee.enums;

/**
 * 返回值枚举类
 */
public enum ResultEnum {

    SUCCESS(0, "success"),
    Unknown_Account(301,"UnknownAccount,账户不存在！"),
    STUDENT_LOGIN(200,"登陆成功，学生用户"),
    ADMAIN_LOGIN(201,"登陆成功，管理员"),
    MORE_PWDERROR_LUCK(102,"输错密码次数过多，用户被锁定！"),
    LOG_FILED(100,"登陆失败"),
    CHECKCODE_ERROR(101,"验证码错误"),
    SAVEUSER_SUCCESS(400,"注册成功"),
    USER_HEAD(401,"用户已存在"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
