package cn.xilikeli.staging.common.advice;

import cn.xilikeli.staging.common.configuration.CodeMessageConfiguration;
import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.HttpException;
import cn.xilikeli.staging.vo.response.UnifyResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 全局异常处理
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 总体文件最大体积
     * 该配置位于 src/main/java/cn/xilikeli/staging/extension/file/config.yml
     */
    @Value("${spring.servlet.multipart.max-file-size:20M}")
    private String maxFileSize;

    /**
     * 处理未归类的异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponseVO<Object> handleException(HttpServletRequest request, Exception e) {
        String method = request.getMethod();
        String requestUrl = request.getRequestURI();

        // 对于未知异常, 统一返回服务器未知错误错误码对应的错误信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.INTERNAL_SERVER_ERROR.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.INTERNAL_SERVER_ERROR.getZhDescription();
        }

        log.error("全局异常处理===处理未知异常: code: {}, message: {}, request: {}, detail: {}",
                CodeEnum.INTERNAL_SERVER_ERROR.getCode(), message, method + " " + requestUrl, e);

        return UnifyResponseVO.failed(CodeEnum.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    /**
     * 处理 Http 自定义异常
     *
     * @param request 当前请求
     * @param e       要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponseVO<Object>> handleHttpException(HttpServletRequest request, HttpException e) {
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        String message;

        if (e.getDefaultMessageFlag()) {
            message = CodeMessageConfiguration.getMessageByCode(e.getCode());
            if (StringUtils.isBlank(message)) {
                message = e.getMessage();
            }
        } else {
            message = e.getMessage();
        }

        log.error("全局异常处理===处理 Http 异常: code: {}, message: {}, request: {}, detail: {}",
                CodeEnum.INTERNAL_SERVER_ERROR.getCode(), message, method + " " + requestUrl, e);

        UnifyResponseVO<Object> unifyResponseVO = UnifyResponseVO.failed(e.getCode(), message);

        // 这里因为没用 Spring 的注解,所以需要 headers 来设置返回的类型
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 获取 http 状态码并设置
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        if (httpStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        // 使用 ResponseEntity 泛型类来返回包含 Http 状态码、响应结果等内容
        return new ResponseEntity<>(unifyResponseVO, headers, httpStatus);
    }

    /**
     * 处理丢失参数异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("全局异常处理===处理丢失参数异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.MISSING_PARAMETER.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.MISSING_PARAMETER.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.MISSING_PARAMETER.getCode(), message);
    }

    /**
     * 处理方法参数类型不匹配异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("全局异常处理===处理方法参数类型不匹配异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getCode(), message);
    }

    /**
     * 处理 ServletException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(ServletException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleServletException(ServletException e) {
        log.error("全局异常处理===处理 ServletException, 详情: ", e);

        return UnifyResponseVO.failed(CodeEnum.FAIL.getCode(), e.getMessage());
    }

    /**
     * 处理 TypeMismatchException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleTypeMismatchException(TypeMismatchException e) {
        log.error("全局异常处理===处理 TypeMismatchException: , 详情: ", e);

        return UnifyResponseVO.failed(CodeEnum.PARAMETER_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理文件体积过大异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(code = HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public UnifyResponseVO<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("全局异常处理===处理文件体积过大异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.FILE_TOO_LARGE.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.FILE_TOO_LARGE.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.FILE_TOO_LARGE.getCode(), message + maxFileSize);
    }

    /**
     * 处理找不到 API 接口异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public UnifyResponseVO<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("全局异常处理===处理找不到 API 接口异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.NOT_FOUND_API_ROUTE.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.NOT_FOUND_API_ROUTE.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.NOT_FOUND_API_ROUTE.getCode(), message);
    }


    /**
     * 处理 Http 消息不可读异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("全局异常处理===处理 Http 消息不可读异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getCode(), message);
    }

    /**
     * 处理 Http 信息转换错误异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleHttpMessageConversionException(HttpMessageConversionException e) {
        log.error("全局异常处理===处理 Http 信息转换错误异常, 详情: ", e);

        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getZhDescription();
        }

        return UnifyResponseVO.failed(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode(), message);
    }

    /**
     * 处理 DTO 字段参数校验异常
     * 该异常处理用于处理 DTO 字段的参数校验
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("全局异常处理===处理 DTO 字段参数校验异常, 详情: ", e);

        // 获取错误信息列表
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 组合错误信息
        String errorMessage = this.formatAllErrorMessages(errors);

        return UnifyResponseVO.failed(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode(), errorMessage);
    }

    /**
     * 处理 url 查询参数校验异常
     * 该异常处理用于处理接口 url 中的查询参数校验
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyResponseVO<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("全局异常处理===处理 url 查询参数校验异常, 详情: ", e);

        // 获取错误信息列表
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        // 组合错误信息
        String errorMessage = formatAllErrorMessages(errors);

        return UnifyResponseVO.failed(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode(), errorMessage);
    }

    /**
     * 将异常错误信息列表中的错误信息拼接到一起并返回
     *
     * @param errors 错误信息列表
     * @return 返回组合后的错误信息字符串
     */
    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();

        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage())
                        .append("; ")
        );

        return errorMsg.toString();
    }

    /**
     * 将异常错误信息列表中的错误信息拼接到一起并返回
     *
     * @param errors 错误信息列表
     * @return 返回组合后的错误信息字符串
     */
    private String formatAllErrorMessages(Set<ConstraintViolation<?>> errors) {
        StringBuffer errorMsg = new StringBuffer();

        errors.forEach(error ->
                errorMsg.append(error.getMessage())
                        .append("; ")
        );

        return errorMsg.toString();
    }

}