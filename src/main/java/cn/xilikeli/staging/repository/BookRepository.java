package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>
 * 图书 Repository 接口
 * </p>
 *
 * @author 踏雪彡寻梅
 * @version 1.0
 * @date 2020/9/24 - 01:27
 * @since JDK1.8
 */
public interface BookRepository extends JpaRepository<Book, Long> {

}