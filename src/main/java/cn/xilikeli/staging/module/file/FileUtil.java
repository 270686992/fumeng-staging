package cn.xilikeli.staging.module.file;

import org.springframework.util.DigestUtils;
import org.springframework.util.unit.DataSize;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * <p>
 * 文件工具类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/28
 * @since JDK1.8
 */
public class FileUtil {

    /**
     * 获取当前系统的文件系统实现
     *
     * @return 返回当前系统的文件系统实现
     */
    public static FileSystem getDefaultFileSystem() {
        return FileSystems.getDefault();
    }

    /**
     * 判断一个指定的文件路径是否为绝对路径
     *
     * @param pathStr 文件路径
     * @return 绝对路径返回 true, 非绝对路径返回 false
     */
    public static boolean isAbsolute(String pathStr) {
        Path path = getDefaultFileSystem().getPath(pathStr);
        return path.isAbsolute();
    }

    /**
     * 初始化本地文件保存目录
     *
     * @param dir 本地文件保存目录需要放置的位置(路径)
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void initStoreDir(String dir) {
        String absDir;

        if (isAbsolute(dir)) {
            // 判断 dir 是否为一个绝对路径, 如果是赋给 absDir
            absDir = dir;
        } else {
            // dir 不是一个绝对路径, 将其放置到项目文件夹中
            // 获取项目根文件夹的绝对路径 cmd
            String cmd = getCmd();
            // 将 cmd 和 dir 生成为一个路径 path
            Path path = getDefaultFileSystem().getPath(cmd, dir);
            // 将 path 转换为绝对路径赋给 absDir
            absDir = path.toAbsolutePath().toString();
        }

        java.io.File file = new File(absDir);

        if (!file.exists()) {
            // 如果该目录不存在, 创建该目录
            file.mkdirs();
        }
    }

    /**
     * 获取当前项目根文件夹的绝对路径
     * 例如: mac/linux 系统中: /xxx/xxx/my-springboot-staging, windows 系统中: C:\xxx\xxx\my-springboot-stagin
     *
     * @return 返回当前项目根文件夹的绝对路径
     */
    public static String getCmd() {
        return System.getProperty("user.dir");
    }

    /**
     * 根据传入的文件路径和文件名获取该文件的绝对路径
     *
     * @param dir      文件路径
     * @param filename 文件名
     * @return 返回指定文件的绝对路径
     */
    public static String getFileAbsolutePath(String dir, String filename) {
        if (isAbsolute(dir)) {
            return getDefaultFileSystem()
                    .getPath(dir, filename)
                    .toAbsolutePath().toString();
        } else {
            return getDefaultFileSystem()
                    .getPath(getCmd(), dir, filename)
                    .toAbsolutePath().toString();
        }
    }

    /**
     * 根据传入的文件名获取该文件的扩展名
     *
     * @param filename 文件名
     * @return 返回指定文件的扩展名
     */
    public static String getFileExt(String filename) {
        int index = filename.lastIndexOf('.');
        return filename.substring(index);
    }

    /**
     * 获取文件的 md5 值
     *
     * @param bytes 文件的字节流
     * @return 返回指定文件的 md5 值
     */
    public static String getFileMd5(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 解析文件大小
     *
     * @param size 文件大小
     * @return 返回文件大小的解析结果
     */
    public static Long parseSize(String size) {
        DataSize singleLimitData = DataSize.parse(size);
        return singleLimitData.toBytes();
    }

}