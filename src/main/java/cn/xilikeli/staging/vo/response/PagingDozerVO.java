package cn.xilikeli.staging.vo.response;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 分页数据统一 VO,带有转换为目标类型的分页数据的功能
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2020/9/24
 * @since JDK1.8
 */
@SuppressWarnings("all")
public class PagingDozerVO<T, K> extends PagingVO {

    /**
     * 构造函数
     * 将查询出来的源数据分页对象构造成目标类型的分页数据视图对象
     *
     * @param pageT  源数据分页对象
     * @param classK 要转换的目标类型
     */
    public PagingDozerVO(Page<T> pageT, Class<K> classK) {
        // 初始化基本参数
        this.initPageParameters(pageT);

        // 获取源数据,转换为目标类型数据
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        List<T> sourceList = pageT.getContent();
        List<K> targetList = new ArrayList<>();
        sourceList.forEach(s -> {
            K targetVO = mapper.map(s, classK);
            targetList.add(targetVO);
        });

        this.setItems(targetList);
    }

}
