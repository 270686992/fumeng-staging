package cn.xilikeli.staging.common.response;

import cn.xilikeli.staging.common.exception.http.FailedException;
import cn.xilikeli.staging.common.util.ResponseUtil;
import cn.xilikeli.staging.vo.UnifyResponseVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * <p>
 * 全局统一响应处理
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/1/31
 * @since JDK1.8
 */
@Slf4j
@ControllerAdvice(basePackages = {"cn.xilikeli.staging.controller"})
public class GlobalResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    @SuppressWarnings("all")
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是 UnifyResponseVO 那就没有必要进行额外的操作, 返回 false
        return !returnType.getParameterType().equals(UnifyResponseVO.class);
    }

    @Override
    @SuppressWarnings("all")
    public Object beforeBodyWrite(Object data, MethodParameter returnType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest request, ServerHttpResponse response) {
        UnifyResponseVO<Object> unifyResponseVO = ResponseUtil.generateUnifyResponseVO();

        if (null == data) {
            // 如果 data 是 null, UnifyResponseVO 不需要设置 data
            return unifyResponseVO;
        } else {
            // String 类型不能直接包装, 所以要进行些特别的处理
            if (returnType.getGenericParameterType().equals(String.class)) {
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    // 因为下面的转换为 Json 字符串响应回去的类型是 text 类型而不是 json 类型, 所以在此处设置一下
                    response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
                    // 将数据包装在 UnifyResponseVO 里后, 再转换为 Json 字符串响应给前端
                    unifyResponseVO.setData(data);
                    return objectMapper.writeValueAsString(unifyResponseVO);
                } catch (JsonProcessingException e) {
                    log.error("GlobalResponseAdvice 处理 String 类型时出错, 详情: ", e);
                    throw new FailedException();
                }
            }

            // 将原本的数据包装在 UnifyResponseVO 里
            unifyResponseVO.setData(data);
            return unifyResponseVO;
        }
    }

}