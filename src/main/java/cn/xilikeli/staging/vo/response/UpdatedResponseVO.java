package cn.xilikeli.staging.vo.response;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 更新资源成功的响应结果
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public class UpdatedResponseVO<T> extends UnifyResponseVO<T> {

    public UpdatedResponseVO() {
        super(CodeEnum.UPDATED.getCode(), CodeEnum.UPDATED.getZhDescription(), CodeEnum.UPDATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(Integer code) {
        super(code, CodeEnum.UPDATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(String message) {
        super(CodeEnum.UPDATED.getCode(), message, CodeEnum.UPDATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(T data) {
        super(CodeEnum.UPDATED.getCode(), CodeEnum.UPDATED.getZhDescription(), CodeEnum.UPDATED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(Integer code, String message) {
        super(code, message, CodeEnum.UPDATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(Integer code, T data) {
        super(code, data, CodeEnum.UPDATED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public UpdatedResponseVO(Integer code, String message, T data) {
        super(code, message, CodeEnum.UPDATED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }

}