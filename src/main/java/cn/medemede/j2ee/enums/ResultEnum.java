package cn.medemede.j2ee.enums;

/**
 * 返回值枚举类
 */
public enum ResultEnum {

    SUCCESS(0, "success"),
    STUDENT_LOGIN(200,"登陆成功，学生用户"),
    ADMAIN_LOGIN(201,"登陆成功，管理员"),
    Unknown_Account(202,"UnknownAccount,账户不存在！"),
    MORE_PWDERROR_LUCK(203,"输错密码次数过多，用户被锁定！"),
    LOG_FILED(204,"登陆失败，请检查用户名和密码"),
    CHECKCODE_ERROR(205,"验证码错误"),
    SAVEUSER_SUCCESS(206,"注册成功"),
    USER_HEAD(207,"用户已存在"),
    UPDATE_STU_SUCCESS(208,"修改个人信息成功"),
    ADD_AC_SUCCESS(209,"添加成功"),
    UPDATE_STU_FAILD(210,"修改个人信息失败"),
    ADD_AC_FAILD(211,"添加失败"),
    UPDATE_AC_FAILD(212,"活动修改失败"),
    UPDATE_AC_SUCCESS(213,"活动修改成功"),
    DELETE_AC_SUCCESS(214,"活动删除成功"),
    DELETE_AC_FAILD(215,"活动删除失败"),
    EXPORT_AC_SUCCESS(216,"活动证明导出成功"),
    EXPORT_AC_FAILD(217,"活动证明导出失败"),
    DELETE_STU_SUCCESS(218,"删除成功")
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
