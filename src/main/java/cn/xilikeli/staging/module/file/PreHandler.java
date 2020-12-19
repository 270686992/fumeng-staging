package cn.xilikeli.staging.module.file;

/**
 * <p>
 * 文件预处理器
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 09:54
 * @since JDK1.8
 */
public interface PreHandler {
    /**
     * 在文件写入本地或者上传到云之前调用此方法
     *
     * @param fileInfo 文件信息
     * @return 返回是否上传; 返回 true, 上传; 若返回 false, 则不上传
     */
    boolean handle(FileInfo fileInfo);
}