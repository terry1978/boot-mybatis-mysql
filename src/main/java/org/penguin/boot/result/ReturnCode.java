package org.penguin.boot.result;

public enum ReturnCode {

    // Success
    OK(0, "OK"),

    // Invalid parameter
    INVALID_PARAMETER(1, "无效的参数"),

    // No Data
    NO_DATA(2, "没有找到相应的数据"),

    // Business Exception
    EXCEPTION(3, "业务异常"),

    UNAUTHORIZED(401, "未授权"),

    RESOURCE_NOT_FOUND(404, "Page Not Found"),

    METHOD_NOT_SUPPORTED(400, "请求方式不被支持"),

    ERROR(500, "服务器端内部异常");

    private final int value;

    private final String reasonPhrase;

    ReturnCode(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}
