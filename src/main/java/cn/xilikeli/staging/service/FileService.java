package cn.xilikeli.staging.service;

import cn.xilikeli.staging.bo.FileBO;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 文件服务类
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:28
 * @since JDK1.8
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param fileMap 文件 map
     * @return 文件数据
     */
    List<FileBO> upload(MultiValueMap<String, MultipartFile> fileMap);

    /**
     * 通过 md5 检查文件是否存在
     *
     * @param md5 md5
     * @return 返回 true 表示已存在, 返回 false 表示不存在
     */
    boolean checkFileExistByMd5(String md5);

}