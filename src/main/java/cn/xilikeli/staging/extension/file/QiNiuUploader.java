package cn.xilikeli.staging.extension.file;

import cn.xilikeli.staging.common.util.DateUtil;
import cn.xilikeli.staging.common.util.LocalCacheUtil;
import cn.xilikeli.staging.module.file.AbstractUploader;
import cn.xilikeli.staging.module.file.FileConstant;
import cn.xilikeli.staging.module.file.FileProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * 文件上传服务实现, 上传到七牛云
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 21:53
 * @since JDK1.8
 */
@Slf4j
public class QiNiuUploader extends AbstractUploader {
    @Autowired
    private FileProperties fileProperties;

    @Value("${staging.file.qiniuyun.access-key}")
    private String accessKey;

    @Value("${staging.file.qiniuyun.secret-key}")
    private String secretKey;

    @Value("${staging.file.qiniuyun.bucket}")
    private String bucket;

    @Value("${staging.file.qiniuyun.upload-token-time-limit}")
    private Long upTokenTimeLimit;

    private UploadManager uploadManager;

    private String upToken;

    private Auth auth;

    /**
     * 因为需要得到 spring-boot 提供的配置，所以不能在 constructor 中初始化
     * 使用 PostConstruct 来初始化
     */
    @PostConstruct
    public void initUploadManager() {
        Configuration cfg = new Configuration(Region.region2());
        this.uploadManager = new UploadManager(cfg);
        this.auth = Auth.create(this.accessKey, this.secretKey);
        Calendar now = Calendar.getInstance();
        this.upToken = this.auth.uploadToken(this.bucket, null, this.upTokenTimeLimit, null);
        // 计算七牛云上传 token 有效期截止时间
        Date upTokenExpiryTime = DateUtil.calculateQiNiuUpTokenExpiryDate(now, this.upTokenTimeLimit.intValue()).getTime();
        // 设置到本地缓存中
        String upTokenExpiryTimeStr = DateUtil.transformDateToStr(upTokenExpiryTime);
        LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + "upTokenExpiryTime", upTokenExpiryTimeStr);
    }

    @Override
    protected FileProperties getFileProperties() {
        return this.fileProperties;
    }

    @Override
    protected boolean handleOneFile(byte[] bytes, String newFilename) {
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try {
            // 从本地缓存中取七牛云上传 token 过期时间
            String upTokenExpiryTimeStr = LocalCacheUtil.getLocalCache(LocalCacheUtil.CACHE_PREFIX + "upTokenExpiryTime");
            Date upTokenExpiryTime = DateUtil.transformStrToDate(upTokenExpiryTimeStr);

            // 判断是否过期
            boolean expired = DateUtil.isOutOfDate(upTokenExpiryTime);
            if (expired) {
                // 过期, 重新生成 token, 并且重新设置过期时间到本地缓存中
                Calendar now = Calendar.getInstance();
                this.upToken = this.auth.uploadToken(this.bucket, null, this.upTokenTimeLimit, null);
                upTokenExpiryTime = DateUtil.calculateQiNiuUpTokenExpiryDate(now, this.upTokenTimeLimit.intValue()).getTime();
                upTokenExpiryTimeStr = DateUtil.transformDateToStr(upTokenExpiryTime);
                LocalCacheUtil.setLocalCache(LocalCacheUtil.CACHE_PREFIX + "upTokenExpiryTime", upTokenExpiryTimeStr);
            }

            Response response = this.uploadManager.put(byteInputStream, newFilename, this.upToken, null, null);
            log.info("七牛云上传文件响应结果: " + response.toString());
            return response.isOK();
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("上传文件到七牛云出现错误, 错误详情: {}", r.error);
            return false;
        }
    }

    @Override
    protected String getStorePath(String newFilename) {
        return this.fileProperties.getDomain() + newFilename;
    }

    @Override
    protected String getFileType() {
        return FileConstant.REMOTE;
    }
}