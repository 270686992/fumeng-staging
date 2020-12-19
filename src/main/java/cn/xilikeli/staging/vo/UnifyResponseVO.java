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
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:23
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
}