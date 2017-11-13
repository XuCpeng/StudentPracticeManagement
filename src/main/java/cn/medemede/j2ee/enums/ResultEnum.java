package cn.medemede.j2ee.enums;

public enum ResultEnum {
    SUCCESS(0, "success"),
    UNKONW_ERROR(-1, "UNKONW ERROR"),

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
