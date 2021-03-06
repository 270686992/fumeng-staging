package cn.xilikeli.staging.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xilikeli.staging.bo.FileBO;
import cn.xilikeli.staging.common.constant.CommonConstant;
import cn.xilikeli.staging.model.FileDO;
import cn.xilikeli.staging.module.file.FileConstant;
import cn.xilikeli.staging.module.file.FileProperties;
import cn.xilikeli.staging.module.file.Uploader;
import cn.xilikeli.staging.repository.FileRepository;
import cn.xilikeli.staging.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 文件服务类实现类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
@Service
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    private Uploader uploader;

    private FileProperties fileProperties;

    @Autowired
    public void setFileRepository(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Autowired
    public void setUploader(Uploader uploader) {
        this.uploader = uploader;
    }

    @Autowired
    public void setFileProperties(FileProperties fileProperties) {
        this.fileProperties = fileProperties;
    }

    @Override
    public List<FileBO> upload(MultiValueMap<String, MultipartFile> fileMap) {
        List<FileBO> res = new ArrayList<>();

        this.uploader.upload(fileMap, fileInfo -> {
            FileDO found = this.fileRepository.findOneByMd5(fileInfo.getMd5());
            // 数据库中不存在
            if (found == null) {
                FileDO fileModel = new FileDO();
                BeanUtil.copyProperties(fileInfo, fileModel);
                this.fileRepository.save(fileModel);
                res.add(transformModelToBo(fileModel, fileInfo.getKey()));
                return true;
            }
            // 已存在，则直接转化返回
            res.add(transformModelToBo(found, fileInfo.getKey()));
            return false;
        });

        return res;
    }

    @Override
    public boolean checkFileExistByMd5(String md5) {
        return this.fileRepository.countByMd5(md5) > 0;
    }

    /**
     * 将文件信息 Model 转换为文件信息 BO
     *
     * @param file 文件信息 Model
     * @param key  文件 key, 上传时指定的
     * @return 返回转换后的文件信息 BO
     */
    private FileBO transformModelToBo(FileDO file, String key) {
        FileBO bo = new FileBO();
        BeanUtil.copyProperties(file, bo);

        if (file.getType().equals(FileConstant.LOCAL)) {
            String s = this.fileProperties.getServePath().split("/")[0];

            if (System.getProperties().getProperty(CommonConstant.OS_NAME_PROPERTY).toUpperCase().contains(CommonConstant.WINDOWS)) {
                // replaceAll 是将 windows 平台下的 \ 替换为 /
                bo.setUrl(this.fileProperties.getDomain() + s + "/" + file.getPath().replaceAll("\\\\", "/"));
            } else {
                bo.setUrl(this.fileProperties.getDomain() + s + "/" + file.getPath());
            }
        } else {
            bo.setUrl(file.getPath());
        }
        bo.setKey(key);

        return bo;
    }

}