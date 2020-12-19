package cn.xilikeli.staging.module.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * 待处理文件信息实体
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 09:53
 * @since JDK1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    /**
     * 真实 url, 用于访问文件
     */
    private String url;

    /**
     * 前端上传的 key
     */
    private String key;

    /**
     * 文件路径
     * 文件类型为 LOCAL 时, 表示文件在本地的路径
     * 文件类型为 LOCAL 时, 表示文件在云空间(OSS)的路径
     */
    private String path;

    /**
     * 文件类型
     * 本地: LOCAL
     * 远程(OSS): REMOTE
     */
    private String type;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 扩展名, 例如: .jpg
     */
    private String extension;

    /**
     * 文件大小
     */
    private Integer size;

    /**
     * md5 值, 防止上传重复文件
     */
    private String md5;
}