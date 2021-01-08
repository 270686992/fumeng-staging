package cn.xilikeli.staging.vo;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * <p>
 * 删除资源成功的响应结果
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:51
 * @since JDK1.8
 */
public class DeletedResponseVO<T> extends UnifyResponseVO<T> {

    public DeletedResponseVO() {
        super(CodeEnum.DELETED.getCode(), CodeEnum.DELETED.getZhDescription(), CodeEnum.DELETED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(Integer code) {
        super(code, CodeEnum.DELETED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(String message) {
        super(CodeEnum.DELETED.getCode(), message, CodeEnum.DELETED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(T data) {
        super(CodeEnum.DELETED.getCode(), CodeEnum.DELETED.getZhDescription(), CodeEnum.DELETED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(Integer code, String message) {
        super(code, message, CodeEnum.DELETED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(Integer code, T data) {
        super(code, data, CodeEnum.DELETED.getSuccess());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    public DeletedResponseVO(Integer code, String message, T data) {
        super(code, message, CodeEnum.DELETED.getSuccess(), data);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.CREATED.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }

}