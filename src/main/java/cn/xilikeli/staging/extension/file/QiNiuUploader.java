package cn.xilikeli.staging.extension.file;

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

import java.io.ByteArrayInputStream;

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

    private UploadManager uploadManager;

    private String upToken;

    public void initUploadManager() {
        Configuration cfg = new Configuration(Region.region2());
        this.uploadManager = new UploadManager(cfg);
        Auth auth = Auth.create(this.accessKey, this.secretKey);
        this.upToken = auth.uploadToken(this.bucket);
    }

    @Override
    protected FileProperties getFileProperties() {
        return this.fileProperties;
    }

    @Override
    protected String getStorePath(String newFilename) {
        return this.fileProperties.getDomain() + newFilename;
    }

    @Override
    protected String getFileType() {
        return FileConstant.REMOTE;
    }

    @Override
    protected boolean handleOneFile(byte[] bytes, String newFilename) {
        initUploadManager();
        ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
        try {
            Response response = this.uploadManager.put(byteInputStream, newFilename, this.upToken, null, null);
            log.info("七牛云上传文件响应结果: {}", response.toString());
            return response.isOK();
        } catch (QiniuException ex) {
            Response r = ex.response;
            log.error("上传文件到七牛云出现错误, 错误详情: {}", r.error);
            return false;
        }
    }

}