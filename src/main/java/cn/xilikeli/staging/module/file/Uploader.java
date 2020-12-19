package cn.xilikeli.staging.module.file;

import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件上传服务接口
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 09:49
 * @since JDK1.8
 */
public interface Uploader {
    /**
     * 上传文件
     *
     * @param fileMap 文件 map
     * @return 文件数据
     */
    List<FileInfo> upload(MultiValueMap<String, MultipartFile> fileMap);

    /**
     * 上传文件
     *
     * @param fileMap    文件 map
     * @param preHandler 文件预处理器
     * @return 文件数据
     */
    List<FileInfo> upload(MultiValueMap<String, MultipartFile> fileMap, PreHandler preHandler);
}