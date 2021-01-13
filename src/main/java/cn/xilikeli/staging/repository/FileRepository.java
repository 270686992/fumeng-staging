package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.FileDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 文件 Repository 接口
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/28 - 20:39
 * @since JDK1.8
 */
public interface FileRepository extends JpaRepository<FileDO, Long> {

    /**
     * 通过文件的 md5 值查询相应的文件
     *
     * @param md5 文件的 md5 值
     * @return 返回 md5 值相应的文件, 查询不到则会返回 null
     */
    FileDO findOneByMd5(String md5);

    /**
     * 通过文件的 md5 值查询存在该 md5 值的文件的数量
     *
     * @param md5 文件的 md5 值
     * @return 返回 md5 值相应的文件数量, 查询不到则会返回 0
     */
    int countByMd5(String md5);

}