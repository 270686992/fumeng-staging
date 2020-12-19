package cn.xilikeli.staging.extension.file;

import cn.xilikeli.staging.module.file.AbstractUploader;
import cn.xilikeli.staging.module.file.FileConstant;
import cn.xilikeli.staging.module.file.FileProperties;
import cn.xilikeli.staging.module.file.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 文件上传服务默认实现, 上传到本地
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:08
 * @since JDK1.8
 */
@Slf4j
public class LocalUploader extends AbstractUploader {
    @Autowired
    private FileProperties fileProperties;

    /**
     * 初始化本地存储文件夹
     */
    @PostConstruct
    public void initStoreDir() {
        // 本地存储需先初始化存储文件夹
        FileUtil.initStoreDir(this.fileProperties.getStoreDir());
    }

    @Override
    protected FileProperties getFileProperties() {
        return this.fileProperties;
    }

    @Override
    protected boolean handleOneFile(byte[] bytes, String newFilename) {
        // 获取文件的绝对路径
        String absolutePath = FileUtil.getFileAbsolutePath(fileProperties.getStoreDir(), getStorePath(newFilename));
        try {
            // 写入文件到本地
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(absolutePath)));
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            log.error("写入文件到本地出现错误, 错误详情: ", e);
            return false;
        }
        return true;
    }

    @Override
    @SuppressWarnings("ResultOfMethodCallIgnored")
    protected String getStorePath(String newFilename) {
        // 获取当前时间并格式化
        Date now = new Date();
        String format = new SimpleDateFormat("yyyy/MM/dd").format(now);

        // 将本地文件保存位置和当前时间组成新文件的基路径
        Path path = Paths.get(this.fileProperties.getStoreDir(), format).toAbsolutePath();

        // 判断该路径是否存在, 不存在则创建
        java.io.File file = new File(path.toString());
        if (!file.exists()) {
            file.mkdirs();
        }

        // 返回新文件的路径
        return Paths.get(format, newFilename).toString();
    }

    @Override
    protected String getFileType() {
        return FileConstant.LOCAL;
    }
}