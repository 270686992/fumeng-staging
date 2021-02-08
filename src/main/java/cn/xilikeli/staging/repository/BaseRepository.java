package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.BaseDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 自定义继承实现 JpaRepository 接口, 实现逻辑删除
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
@NoRepositoryBean
@RepositoryRestResource(exported = false)
@SuppressWarnings("all")
public interface BaseRepository<T extends BaseDO, IdT extends Long> extends JpaRepository<T, IdT> {

    @Query("update #{#entityName} set deleteTime = current_timestamp where id = ?1 and deleteTime is null")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void delete(IdT id);

    @Override
    @Transactional(rollbackFor = Exception.class)
    default void delete(T entity) {
        delete((IdT) entity.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    default void delete(Iterable<? extends T> entities) {
        entities.forEach(entity -> delete((IdT) entity.getId()));
    }

    @Override
    @Query("update #{#entityName} set deleteTime = current_timestamp where deleteTime is null")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteAll();

    @Query("update #{#entityName} set deleteTime = current_timestamp where id in ?1 and deleteTime is null ")
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    void deleteInBatch(List<IdT> ids);

}