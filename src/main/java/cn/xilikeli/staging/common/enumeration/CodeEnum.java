package cn.xilikeli.staging.common.enumeration;

/**
 * <p>
 * 消息码枚举类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:27
 * @since JDK1.8
 */
public enum CodeEnum {

    /**
     * 通用成功消息码
     */
    SUCCESS(0, "OK", "成功", true),

    /**
     * 创建成功消息码
     */
    CREATED(1, "Created", "创建成功", true),

    /**
     * 更新成功消息码
     */
    UPDATED(2, "Updated", "更新成功", true),

    /**
     * 删除成功消息码
     */
    DELETED(3, "Deleted", "删除成功", true),

    /**
     * 服务器未知错误消息码
     */
    INTERNAL_SERVER_ERROR(9999, "Internal Server Error", "服务器未知错误", false),

    /**
     * 通用错误消息码
     */
    UNIVERSAL_ERROR(10000, "Universal Error", "通用错误", false),

    /**
     * 参数错误消息码
     */
    PARAMETER_ERROR(10001, "Parameters Error", "参数错误", false),

    /**
     * 请求体不可为空消息码
     */
    REQUEST_BODY_CANNOT_EMPTY(10002, "Request Body Cannot Empty", "请求体不可为空", false),

    /**
     * 找不到路由消息码
     */
    NOT_FOUND_ROUTE(10003, "Not Found Route", "找不到相应的视图处理器", false),

    /**
     * 通用失败消息码
     */
    FAIL(10004, "Failed", "失败", false),

    /**
     * 认证失败消息码
     */
    UN_AUTHENTICATION(10005, "Authentication Failed", "认证失败", false),

    /**
     * 授权失败消息码
     */
    UN_AUTHORIZATION(10006, "Authorization Failed", "授权失败", false),

    /**
     * 资源不存在消息码
     */
    NOT_FOUND(10007, "Not Found", "资源不存在", false),

    /**
     * 令牌失效消息码
     */
    TOKEN_INVALID(10008, "Token Invalid", "令牌失效", false),

    /**
     * 令牌过期消息码
     */
    TOKEN_EXPIRED(10009, "Token Expired", "令牌过期", false),

    /**
     * 字段重复消息码
     */
    DUPLICATED(10010, "Duplicated", "字段重复", false),

    /**
     * 禁止操作消息码
     */
    FORBIDDEN(10011, "Forbidden", "禁止操作", false),

    /**
     * 请求方法不允许消息码
     */
    METHOD_NOT_ALLOWED(10012, "Method Not Allowed", "请求方法不允许", false),

    /**
     * 刷新令牌获取失败消息码
     */
    REFRESH_TOKEN_FAILED(10013, "Get Refresh Token Failed", "刷新令牌获取失败", false),

    /**
     * 文件体积过大消息码
     */
    FILE_TOO_LARGE(10014, "File Too Large", "文件体积过大, 全部文件大小不能超过", false),

    /**
     * 文件数量过多消息码
     */
    FILE_TOO_MANY(10015, "File Too Many", "文件数量过多", false),

    /**
     * 文件扩展名不符合规范消息码
     */
    FILE_EXTENSION(10016, "File Extension Not Allowed", "文件扩展名不符合规范", false),

    /**
     * 请求过于频繁, 请稍后重试消息码
     */
    REQUEST_LIMIT(10017, "Too Many Requests", "请求过于频繁, 请稍后重试", false),

    /**
     * 丢失参数消息码
     */
    MISSING_PARAMETER(10018, "Missing Parameter", "丢失参数", false),

    /**
     * 方法参数类型不匹配消息码
     */
    METHOD_ARGUMENT_TYPE_MISMATCH(10019, "Method Argument Type Mismatch", "方法参数类型不匹配", false),

    /**
     * Http 信息转换错误消息码
     */
    HTTP_MESSAGE_CONVERSION_ERROR(10020, "Http Message Conversion Error", "Http 信息转换错误, 请检查请求参数是否正确", false),

    /**
     * 未找到文件消息码
     */
    NOT_FOUND_FILE(10021, "Not Found File", "未找到文件", false),

    /**
     * 读取文件数据失败消息码
     */
    READ_FILE_FAILED(10022, "Failed To Read File Data", "读取文件数据失败", false);

    /**
     * 消息码
     */
    private final Integer code;

    /**
     * 英文描述
     */
    private final String description;

    /**
     * 中文描述
     */
    private final String zhDescription;

    /**
     * 是否成功
     */
    private final Boolean success;

    CodeEnum(Integer code, String description, String zhDescription, Boolean success) {
        this.code = code;
        this.description = description;
        this.zhDescription = zhDescription;
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getZhDescription() {
        return zhDescription;
    }

    public Boolean getSuccess() {
        return success;
    }

}