package cn.xilikeli.staging.vo;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.util.RequestUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * <p>
 * 统一 API 响应结果
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "统一 API 响应结果", description = "统一 API 响应结果")
public class UnifyResponseVO<T> {

    /**
     * 响应消息码
     */
    @ApiModelProperty(value = "响应消息码")
    private Integer code;

    /**
     * 响应信息
     */
    @ApiModelProperty(value = "响应信息")
    private String message;

    /**
     * 是否成功
     */
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    /**
     * 响应数据
     */
    @ApiModelProperty(value = "响应数据")
    private T data;

    /**
     * 请求的 API 接口
     */
    @ApiModelProperty(value = "请求的 API 接口")
    private String request;

    public boolean isSuccess() {
        return this.success;
    }

    @ApiModelProperty(value = "是否不成功")
    public boolean isUnSuccess() {
        return !this.success;
    }

    /**
     * 统一 API 响应结果默认构造函数
     */
    public UnifyResponseVO() {
        this.code = CodeEnum.SUCCESS.getCode();
        this.message = CodeEnum.SUCCESS.getDescription();
        this.success = CodeEnum.SUCCESS.getSuccess();
        this.data = null;
        this.request = RequestUtil.getSimpleRequest();
    }

    /**
     * 构造统一 API 响应结果
     *
     * @param data 响应数据
     */
    public UnifyResponseVO(T data) {
        this.code = CodeEnum.SUCCESS.getCode();
        this.message = CodeEnum.SUCCESS.getDescription();
        this.success = CodeEnum.SUCCESS.getSuccess();
        this.data = data;
        this.request = RequestUtil.getSimpleRequest();
    }

    /**
     * 构造统一 API 响应结果
     *
     * @param code    响应消息码
     * @param success 是否成功
     */
    public UnifyResponseVO(Integer code, Boolean success) {
        this.code = code;
        this.message = null;
        this.success = success;
        this.data = null;
        this.request = RequestUtil.getSimpleRequest();
    }

    /**
     * 构造统一 API 响应结果
     *
     * @param code    响应消息码
     * @param message 响应消息
     * @param success 是否成功
     */
    public UnifyResponseVO(Integer code, String message, Boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = null;
        this.request = RequestUtil.getSimpleRequest();
    }

    /**
     * 构造统一 API 响应结果
     *
     * @param code    响应消息码
     * @param data    响应数据
     * @param success 是否成功
     */
    public UnifyResponseVO(Integer code, T data, Boolean success) {
        this.code = code;
        this.message = null;
        this.success = success;
        this.data = data;
        this.request = RequestUtil.getSimpleRequest();
    }

    /**
     * 构造统一 API 响应结果
     *
     * @param code    响应消息码
     * @param message 响应消息
     * @param success 是否成功
     * @param data    响应数据
     */
    public UnifyResponseVO(Integer code, String message, Boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
        this.request = RequestUtil.getSimpleRequest();
    }

    public static <T> UnifyResponseVO<T> succeed() {
        return new UnifyResponseVO<>();
    }

    public static <T> UnifyResponseVO<T> succeed(Integer code) {
        return new UnifyResponseVO<>(code, true);
    }

    public static <T> UnifyResponseVO<T> succeed(String message) {
        return new UnifyResponseVO<>(CodeEnum.SUCCESS.getCode(), message, true);
    }

    public static <T> UnifyResponseVO<T> succeed(Integer code, String message) {
        return new UnifyResponseVO<>(code, message, true);
    }

    public static <T> UnifyResponseVO<T> succeedWith(T data) {
        return new UnifyResponseVO<>(data);
    }

    public static <T> UnifyResponseVO<T> succeedWith(T data, Integer code) {
        return new UnifyResponseVO<>(code, data, true);
    }

    public static <T> UnifyResponseVO<T> succeedWith(T data, String message) {
        return new UnifyResponseVO<>(CodeEnum.SUCCESS.getCode(), message, true, data);
    }

    public static <T> UnifyResponseVO<T> succeedWith(T data, Integer code, String message) {
        return new UnifyResponseVO<>(code, message, true, data);
    }

    public static <T> UnifyResponseVO<T> failed() {
        return new UnifyResponseVO<>(CodeEnum.UNIVERSAL_ERROR.getCode(), CodeEnum.UNIVERSAL_ERROR.getDescription(), false);
    }

    public static <T> UnifyResponseVO<T> failed(Integer code) {
        return new UnifyResponseVO<>(code, false);
    }

    public static <T> UnifyResponseVO<T> failed(String message) {
        return new UnifyResponseVO<>(CodeEnum.UNIVERSAL_ERROR.getCode(), message, false);
    }

    public static <T> UnifyResponseVO<T> failed(Integer code, String message) {
        return new UnifyResponseVO<>(code, message, false);
    }

    public static <T> UnifyResponseVO<T> failedWith(T data) {
        return new UnifyResponseVO<>(CodeEnum.UNIVERSAL_ERROR.getCode(), CodeEnum.UNIVERSAL_ERROR.getDescription(), false, data);
    }

    public static <T> UnifyResponseVO<T> failedWith(T data, Integer code) {
        return new UnifyResponseVO<>(code, data, false);
    }

    public static <T> UnifyResponseVO<T> failedWith(T data, String message) {
        return new UnifyResponseVO<>(CodeEnum.UNIVERSAL_ERROR.getCode(), message, false, data);
    }

    public static <T> UnifyResponseVO<T> failedWith(T data, Integer code, String message) {
        return new UnifyResponseVO<>(code, message, false, data);
    }

}