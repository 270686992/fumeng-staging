package cn.xilikeli.staging.module.file;

import cn.xilikeli.staging.common.factory.YmlPropertySourceFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 文件上传属性配置类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 10:22
 * @since JDK1.8
 */
@Component
@ConfigurationProperties("staging.file")
@PropertySource(
        value = "classpath:cn/xilikeli/staging/extension/file/config.yml",
        encoding = "UTF-8", factory = YmlPropertySourceFactory.class)
public class FileProperties {
    private static final String[] DEFAULT_EMPTY_ARRAY = new String[0];

    /**
     * 本地文件保存位置
     */
    private String storeDir = "/assets";

    /**
     * 单个文件最大体积
     */
    private String singleLimit = "10MB";

    /**
     * 文件最大数量
     */
    private Integer nums = 10;

    /**
     * 文件服务域名
     */
    private String domain;

    /**
     * 排除文件类型
     */
    private String[] exclude = DEFAULT_EMPTY_ARRAY;

    /**
     * 包括文件类型
     */
    private String[] include = DEFAULT_EMPTY_ARRAY;

    /**
     * 文件默认存储路径
     */
    private String servePath = "assets/**";

    public String getServePath() {
        return servePath;
    }

    public void setServePath(String servePath) {
        this.servePath = servePath;
    }

    public String getStoreDir() {
        return storeDir;
    }

    public void setStoreDir(String storeDir) {
        this.storeDir = storeDir;
    }

    public String getSingleLimit() {
        return singleLimit;
    }

    public void setSingleLimit(String singleLimit) {
        this.singleLimit = singleLimit;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String[] getExclude() {
        return exclude;
    }

    public void setExclude(String[] exclude) {
        this.exclude = exclude;
    }

    public String[] getInclude() {
        return include;
    }

    public void setInclude(String[] include) {
        this.include = include;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}