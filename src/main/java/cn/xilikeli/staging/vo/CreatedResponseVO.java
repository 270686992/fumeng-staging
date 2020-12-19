package cn.xilikeli.staging.vo;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 创建资源成功的响应结果
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:43
 * @since JDK1.8
 */
public class CreatedResponseVO<T> extends UnifyResponseVO<T> {
    public CreatedResponseVO() {
        super(CodeEnum.CREATED.getCode(), CodeEnum.CREATED.getZhDescription(), CodeEnum.CREATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(Integer code) {
        super(code, CodeEnum.CREATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(String message) {
        super(CodeEnum.CREATED.getCode(), message, CodeEnum.CREATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(T data) {
        super(CodeEnum.CREATED.getCode(), CodeEnum.CREATED.getZhDescription(), CodeEnum.CREATED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(Integer code, String message) {
        super(code, message, CodeEnum.CREATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(Integer code, T data) {
        super(code, data, CodeEnum.CREATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public CreatedResponseVO(Integer code, String message, T data) {
        super(code, message, CodeEnum.CREATED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}