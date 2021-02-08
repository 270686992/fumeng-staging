package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.AccountDO;

/**
 * <p>
 * 用户基本信息 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public interface AccountRepository extends BaseRepository<AccountDO, Long> {

    /**
     * 通过邮箱查询存在该邮箱的用户信息的数量
     *
     * @param email 邮箱
     * @return 返回相应的用户信息数量, 查询不到则会返回 0
     */
    int countByEmail(String email);

    /**
     * 通过手机号查询存在该手机号的用户信息的数量
     *
     * @param mobile 手机号
     * @return 返回相应的用户信息数量, 查询不到则会返回 0
     */
    int countByMobile(String mobile);

}