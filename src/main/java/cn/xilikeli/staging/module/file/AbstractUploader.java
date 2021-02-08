package cn.xilikeli.staging.module.file;

import cn.xilikeli.staging.common.enumeration.CodeEnum;
import cn.xilikeli.staging.common.exception.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 文件上传服务抽象基类, 模版
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
public abstract class AbstractUploader implements Uploader {

    /**
     * 文件预处理器
     */
    private PreHandler preHandler;

    /**
     * 得到文件配置
     *
     * @return 文件配置
     */
    protected abstract FileProperties getFileProperties();

    /**
     * 处理一个文件
     *
     * @param bytes       文件数据, 比特流
     * @param newFilename 新文件名称
     * @return 返回处理是否成功
     */
    protected abstract boolean handleOneFile(byte[] bytes, String newFilename);

    /**
     * 获取文件的存储路径
     *
     * @param newFilename 文件名
     * @return 返回文件的存储路径
     */
    protected abstract String getStorePath(String newFilename);

    /**
     * 返回文件存储位置类型
     *
     * @return LOCAL ｜ REMOTE
     */
    protected abstract String getFileType();

    @Override
    public List<FileInfo> upload(MultiValueMap<String, MultipartFile> fileMap) {
        // 检查文件 map
        checkFileMap(fileMap);
        // 处理文件 map
        return handleMultipartFiles(fileMap);
    }

    @Override
    public List<FileInfo> upload(MultiValueMap<String, MultipartFile> fileMap, PreHandler preHandler) {
        this.preHandler = preHandler;
        return this.upload(fileMap);
    }

    /**
     * 检查文件 map
     *
     * @param fileMap 文件 map
     */
    protected void checkFileMap(MultiValueMap<String, MultipartFile> fileMap) {
        // 判断是否为空
        if (fileMap.isEmpty()) {
            throw new NotFoundException(CodeEnum.NOT_FOUND_FILE.getCode());
        }

        // 判断是否超过最大数量
        int nums = getFileProperties().getNums();
        if (fileMap.size() > nums) {
            throw new FileTooManyException(CodeEnum.FILE_TOO_MANY.getCode());
        }
    }

    /**
     * 处理文件 map
     *
     * @param fileMap 文件 map
     * @return 返回处理后的文件数据
     */
    protected List<FileInfo> handleMultipartFiles(MultiValueMap<String, MultipartFile> fileMap) {
        // 得到单个文件的大小限制
        long singleFileLimit = getSingleFileLimit();
        // 处理结果集
        List<FileInfo> res = new ArrayList<>();
        fileMap.keySet().forEach(key -> fileMap.get(key).forEach(file -> {
            if (!file.isEmpty()) {
                // 处理各个文件
                handleOneFile0(res, singleFileLimit, file);
            }
        }));
        return res;
    }

    /**
     * 得到单个文件的大小限制
     *
     * @return 返回单个文件的大小限制
     */
    private long getSingleFileLimit() {
        String singleLimit = getFileProperties().getSingleLimit();
        return FileUtil.parseSize(singleLimit);
    }

    /**
     * 处理单个文件
     *
     * @param res             保存文件处理结果的结果集
     * @param singleFileLimit 单个文件的大小限制
     * @param file            处理的文件
     */
    private void handleOneFile0(List<FileInfo> res, long singleFileLimit, MultipartFile file) {
        // 获取文件的字节
        byte[] bytes = getFileBytes(file);
        // 获取包括的文件类型
        String[] include = getFileProperties().getInclude();
        // 获取排除的文件类型
        String[] exclude = getFileProperties().getExclude();
        // 检查文件并获取扩展名
        String ext = checkOneFile(include, exclude, singleFileLimit, file.getOriginalFilename(), bytes.length);
        // 生成新的文件名
        String newFilename = getNewFilename(ext);
        // 生成并获取文件路径
        String storePath = getStorePath(newFilename);
        // 生成文件的 md5 值
        String md5 = FileUtil.getFileMd5(bytes);
        // 生成待处理文件信息
        FileInfo fileInfo = FileInfo.builder().
                name(newFilename).
                md5(md5).
                key(file.getName()).
                path(storePath).
                size(bytes.length).
                type(getFileType()).
                extension(ext).
                build();
        // 如果预处理器不为空, 且处理结果为 false, 直接返回, 否则处理
        if (preHandler != null && !preHandler.handle(fileInfo)) {
            return;
        }

        // 处理文件, 写入本地或上传到云是否成功
        boolean ok = handleOneFile(bytes, newFilename);
        if (ok) {
            res.add(fileInfo);
        }
    }

    /**
     * 生成新文件名称
     *
     * @param ext 文件后缀
     * @return 返回生成的新名称
     */
    protected String getNewFilename(String ext) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + ext;
    }

    /**
     * 获得文件的字节
     *
     * @param file 文件
     * @return 返回文件的字节
     */
    protected byte[] getFileBytes(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (Exception e) {
            throw new FailedException(CodeEnum.READ_FILE_FAILED.getCode());
        }
        return bytes;
    }

    /**
     * 单个文件检查
     *
     * @param include         包含的文件类型
     * @param exclude         排除的文件类型
     * @param singleFileLimit 单个文件大小限制
     * @param originName      文件原始名称
     * @param length          文件大小
     * @return 返回文件的扩展名, 例如: .jpg
     */
    protected String checkOneFile(String[] include, String[] exclude, long singleFileLimit, String originName, int length) {
        // 根据文件原始名称获取扩展名
        String ext = FileUtil.getFileExt(originName);
        // 检测扩展名
        if (!this.checkExt(include, exclude, ext)) {
            throw new FileExtensionException(ext + "文件类型不支持");
        }
        // 检测单个大小
        if (length > singleFileLimit) {
            throw new FileTooLargeException(originName + "文件不能超过" + singleFileLimit);
        }
        return ext;
    }

    /**
     * 检查文件后缀名是否被包含或者被排除
     *
     * @param include 包含的文件类型
     * @param exclude 排除的文件类型
     * @param ext     后缀名
     * @return 返回是否通过, 通过返回 true, 不通过返回 false
     */
    protected boolean checkExt(String[] include, String[] exclude, String ext) {
        int inLen = include == null ? 0 : include.length;
        int exLen = exclude == null ? 0 : exclude.length;

        // 如果两者都有取 include, 反之有一者则用一者
        if (inLen > 0 && exLen > 0) {
            return this.findInInclude(include, ext);
        } else if (inLen > 0) {
            // 有 include, 无 exclude
            return this.findInInclude(include, ext);
        } else if (exLen > 0) {
            // 有 exclude, 无 include
            return this.findInExclude(exclude, ext);
        } else {
            // 二者都没有
            return true;
        }
    }

    /**
     * 判断文件扩展名是否处于被包含的文件类型中
     *
     * @param include 包含的文件类型
     * @param ext     文件扩展名
     * @return 处于返回 true, 不处于返回 false
     */
    protected boolean findInInclude(String[] include, String ext) {
        for (String s : include) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断文件扩展名是否处于被排除的文件类型中
     *
     * @param exclude 排除的文件类型
     * @param ext     文件扩展名
     * @return 处于返回 true, 不处于返回 false
     */
    protected boolean findInExclude(String[] exclude, String ext) {
        for (String s : exclude) {
            if (s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

}