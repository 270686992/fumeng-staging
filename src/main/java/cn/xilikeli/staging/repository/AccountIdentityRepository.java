package cn.xilikeli.staging.repository;

import cn.xilikeli.staging.model.AccountIdentityDO;

import java.util.Optional;

/**
 * <p>
 * 用户认证信息 Repository 接口
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public interface AccountIdentityRepository extends BaseRepository<AccountIdentityDO, Long> {

    /**
     * 根据标识查询用户认证信息
     *
     * @param identifier 标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     * @return 返回相应的用户认证信息
     */
    Optional<AccountIdentityDO> findByIdentifier(String identifier);

    /**
     * 根据用户 id 和登录类型查询用户认证信息
     *
     * @param accountId    用户 id
     * @param identityType 登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)
     * @return 返回相应的用户认证信息
     */
    Optional<AccountIdentityDO> findByAccountIdAndIdentityType(Long accountId, String identityType);

    /**
     * 通过标识查询存在该标识的用户认证信息的数量
     *
     * @param identifier 标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     * @return 返回相应的用户认证信息数量, 查询不到则会返回 0
     */
    int countByIdentifier(String identifier);

}