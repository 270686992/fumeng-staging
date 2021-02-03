package cn.xilikeli.staging.common.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 消息码配置类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@SuppressWarnings("ConfigurationProperties")
@Component
@ConfigurationProperties
@PropertySource(value = "classpath:code-message.properties", encoding = "UTF-8")
public class CodeMessageConfiguration {

    /**
     * 用于存储配置文件中的 code 码和提示信息
     */
    private static Map<Integer, String> codeMessageMap = new HashMap<>();

    public static String getMessageByCode(Integer code) {
        return codeMessageMap.get(code);
    }

    public Map<Integer, String> getCodeMessage() {
        return codeMessageMap;
    }

    public void setCodeMessage(Map<Integer, String> codeMessage) {
        CodeMessageConfiguration.codeMessageMap = codeMessage;
    }

}