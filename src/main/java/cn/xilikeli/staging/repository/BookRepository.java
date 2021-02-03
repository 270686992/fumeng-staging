package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.BookDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 图书 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
public interface BookRepository extends JpaRepository<BookDO, Long> {

}