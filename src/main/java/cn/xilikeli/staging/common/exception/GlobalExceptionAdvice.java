package cn.xilikeli.staging.common.exception;

import cn.xilikeli.staging.common.configuration.CodeMessageConfiguration;
import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.HttpException;
import cn.xilikeli.staging.vo.UnifyResponseVO;
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
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 14:53
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
    public UnifyResponseVO<Object> handleException(Exception e) {
        log.error("全局处理异常->未归类的异常(Exception), 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.INTERNAL_SERVER_ERROR.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.INTERNAL_SERVER_ERROR.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.INTERNAL_SERVER_ERROR.getCode(), message, false);
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

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(e.getCode());
        if (StringUtils.isBlank(message)) {
            message = e.getMessage();
        }

        log.error("全局处理异常->Http 自定义异常(HttpException): code: {}, message: {}, request: {}, detail: ", e.getCode(), message, method + " " + requestUrl, e);

        UnifyResponseVO<Object> unifyResponseVO = new UnifyResponseVO<>(e.getCode(), message, false);

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
     * 处理 MissingServletRequestParameterException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("全局处理异常-MissingServletRequestParameterException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.MISSING_PARAMETER.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.MISSING_PARAMETER.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.MISSING_PARAMETER.getCode(), message, false);
    }

    /**
     * 处理 MethodArgumentTypeMismatchException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("全局处理异常-MethodArgumentTypeMismatchException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.METHOD_ARGUMENT_TYPE_MISMATCH.getCode(), message, false);
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
        log.error("全局处理异常-ServletException, 详情: ", e);

        return new UnifyResponseVO<>(CodeEnum.FAIL.getCode(), e.getMessage(), false);
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
        log.error("全局处理异常-TypeMismatchException: , 详情: ", e);

        return new UnifyResponseVO<>(CodeEnum.PARAMETER_ERROR.getCode(), e.getMessage(), false);
    }

    /**
     * 处理 MaxUploadSizeExceededException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(code = HttpStatus.PAYLOAD_TOO_LARGE)
    @ResponseBody
    public UnifyResponseVO<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        log.error("全局处理异常-MaxUploadSizeExceededException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.FILE_TOO_LARGE.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.FILE_TOO_LARGE.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.FILE_TOO_LARGE.getCode(), message + maxFileSize, false);
    }

    /**
     * 处理 NoHandlerFoundException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public UnifyResponseVO<Object> handleNoHandlerFoundException(NoHandlerFoundException e) {
        log.error("全局处理异常-NoHandlerFoundException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.NOT_FOUND_ROUTE.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.NOT_FOUND_ROUTE.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.NOT_FOUND_ROUTE.getCode(), message, false);
    }


    /**
     * 处理 handleHttpMessageNotReadableException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("全局处理异常-HttpMessageNotReadableException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.REQUEST_BODY_CANNOT_EMPTY.getCode(), message, false);
    }

    /**
     * 处理 HttpMessageConversionException
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleHttpMessageConversionException(HttpMessageConversionException e) {
        log.error("全局处理异常-HttpMessageConversionException, 详情: ", e);

        // 通过 code 码得到对应的提示信息
        String message = CodeMessageConfiguration.getMessageByCode(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode());
        if (StringUtils.isBlank(message)) {
            message = CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getZhDescription();
        }

        return new UnifyResponseVO<>(CodeEnum.HTTP_MESSAGE_CONVERSION_ERROR.getCode(), message, false);
    }

    /**
     * 处理 MethodArgumentNotValidException 参数校验异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponseVO<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("全局处理异常-MethodArgumentNotValidException, 详情: ", e);

        // 获取错误信息列表
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        // 组合错误信息
        String errorMessage = this.formatAllErrorMessages(errors);

        return new UnifyResponseVO<>(CodeEnum.PARAMETER_ERROR.getCode(), errorMessage, false);
    }

    /**
     * 处理 ConstraintViolationException 参数校验异常
     *
     * @param e 要处理的异常
     * @return 返回处理后的提示信息
     */
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public UnifyResponseVO<Object> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("全局处理异常-ConstraintViolationException, 详情: ", e);

        // 获取错误信息列表
        Set<ConstraintViolation<?>> errors = e.getConstraintViolations();
        // 组合错误信息
        String errorMessage = formatAllErrorMessages(errors);

        return new UnifyResponseVO<>(CodeEnum.PARAMETER_ERROR.getCode(), errorMessage, false);
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