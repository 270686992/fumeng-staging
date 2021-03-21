package cn.xilikeli.staging.common.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 通用消息码枚举
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public enum CodeEnum {

    /**
     * 未定义
     */
    UNDEFINED(-1, "Undefined", "未定义", false),

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
    PARAMETER_ERROR(10001, "Parameters Error", "通用参数错误", false),

    /**
     * 通用操作失败消息码
     */
    FAIL(10002, "Failed", "操作失败", false),

    /**
     * 请求体不可为空消息码
     */
    REQUEST_BODY_CANNOT_EMPTY(10003, "Request Body Cannot Empty", "请求体不可为空", false),

    /**
     * 找不到 API 接口消息码
     */
    NOT_FOUND_API_ROUTE(10004, "Not Found API Route", "找不到相应的 API 接口", false),

    /**
     * 字段重复消息码
     */
    DUPLICATED(10005, "Duplicated", "字段重复", false),

    /**
     * 请求方法不允许消息码
     */
    METHOD_NOT_ALLOWED(10006, "Method Not Allowed", "请求方法不允许", false),

    /**
     * 丢失参数消息码
     */
    MISSING_PARAMETER(10007, "Missing Parameter", "丢失参数", false),

    /**
     * 方法参数类型不匹配消息码
     */
    METHOD_ARGUMENT_TYPE_MISMATCH(10008, "Method Argument Type Mismatch", "方法参数类型不匹配", false),

    /**
     * Http 信息转换错误消息码
     */
    HTTP_MESSAGE_CONVERSION_ERROR(10009, "Http Message Conversion Error", "Http 信息转换错误, 请检查请求参数是否正确", false),

    /**
     * 资源不存在消息码
     */
    NOT_FOUND(10010, "Not Found", "资源不存在", false),

    /**
     * 请求过于频繁, 请稍后重试消息码
     */
    REQUEST_LIMIT(10011, "Too Many Requests", "请求过于频繁, 请稍后重试", false),

    /**
     * 认证失败消息码
     */
    UN_AUTHENTICATION(10012, "Authentication Failed", "认证失败", false),

    /**
     * 授权失败消息码
     */
    UN_AUTHORIZATION(10013, "Authorization Failed", "授权失败", false),

    /**
     * 禁止操作消息码
     */
    FORBIDDEN(10014, "Forbidden", "禁止操作", false),

    /**
     * 令牌失效消息码
     */
    TOKEN_INVALID(10015, "Token Invalid", "令牌失效", false),

    /**
     * 令牌过期消息码
     */
    TOKEN_EXPIRED(10016, "Token Expired", "令牌过期", false),

    /**
     * 刷新令牌获取失败消息码
     */
    REFRESH_TOKEN_FAILED(10017, "Get Refresh Token Failed", "刷新令牌获取失败", false),

    /**
     * 文件体积过大消息码
     */
    FILE_TOO_LARGE(10018, "File Too Large", "文件体积过大, 全部文件大小不能超过", false),

    /**
     * 文件数量过多消息码
     */
    FILE_TOO_MANY(10019, "File Too Many", "文件数量过多", false),

    /**
     * 文件扩展名不符合规范消息码
     */
    FILE_EXTENSION(10020, "File Extension Not Allowed", "文件扩展名不符合规范", false),

    /**
     * 未找到文件消息码
     */
    NOT_FOUND_FILE(10021, "Not Found File", "未找到文件", false),

    /**
     * 读取文件数据失败消息码
     */
    READ_FILE_FAILED(10022, "Failed To Read File Data", "读取文件数据失败", false),

    /**
     * 入参不可为空消息码
     */
    PARAMETER_NOT_NULL(10023, "Parameter Can Be Not Null", "入参不可为空", false);

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
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String getZhDescription() {
        return this.zhDescription;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public static CodeEnum toEnum(Integer code) {
        return Stream.of(CodeEnum.values())
                .filter(errorCodeEnum -> errorCodeEnum.code.equals(code))
                .findAny()
                .orElse(UNDEFINED);
    }

    public static String getDescriptionByCode(Integer code) {
        return Stream.of(CodeEnum.values())
                .filter(codeEnum -> codeEnum.code.equals(code))
                .map(CodeEnum::getDescription)
                .findAny()
                .orElse("");
    }

    public static String getZhDescriptionByCode(Integer code) {
        return Stream.of(CodeEnum.values())
                .filter(codeEnum -> codeEnum.code.equals(code))
                .map(CodeEnum::getZhDescription)
                .findAny()
                .orElse("");
    }

    public static List<CodeEnum> getAllEffectiveEnum() {
        return Arrays.stream(CodeEnum.values())
                .filter(codeEnum -> codeEnum.code > UNDEFINED.code)
                .collect(Collectors.toList());
    }

}