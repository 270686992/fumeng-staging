package cn.xilikeli.staging.service;

import cn.xilikeli.staging.model.AccountIdentityDO;

/**
 * <p>
 * 用户认证信息服务类
 * </p>
 *
 * @author txxunmei
 * @version 1.0
 * @date 2021/2/4
 * @since JDK1.8
 */
public interface AccountIdentityService {

    /**
     * 根据标识检查用户是否存在
     *
     * @param identifier 标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     * @return 存在返回 true, 不存在返回 false
     */
    boolean checkAccountExistByIdentifier(String identifier);

    /**
     * 新建用户认证信息
     *
     * @param accountId    用户 ID
     * @param identityType 登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)
     * @param identifier   标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     * @param credential   密码凭证(站内的保存密码, 站外的不保存或保存 token)
     */
    void createIdentity(Long accountId, String identityType, String identifier, String credential);

    /**
     * 新建用户认证信息
     *
     * @param accountIdentityDO 用户认证信息
     */
    void createIdentity(AccountIdentityDO accountIdentityDO);

    /**
     * 新建用户认证信息 (USERNAME_PASSWORD)
     *
     * @param accountId 用户 ID
     * @param username  用户名
     * @param password  密码
     */
    void createUsernamePasswordIdentity(Long accountId, String username, String password);

    /**
     * 验证用户认证信息 (USERNAME_PASSWORD)
     *
     * @param accountId 用户id
     * @param username  用户名
     * @param password  密码
     * @return 验证成功返回 true, 验证失败返回 false
     */
    boolean verifyUsernamePassword(Long accountId, String username, String password);

    /**
     * 根据标识获取用户认证信息
     *
     * @param identifier 标识(手机号, 邮箱, 用户名或第三方应用的唯一标识)
     * @return 返回相应的用户认证信息
     */
    AccountIdentityDO getIdentityByIdentifier(String identifier);

    /**
     * 根据用户 ID 和登录类型获取用户认证信息
     *
     * @param accountId    用户 ID
     * @param identityType 登录类型(手机号, 邮箱, 用户名或第三方应用名称(微信, 微博, QQ 等)
     * @return 返回相应的用户认证信息
     */
    AccountIdentityDO getIdentityByAccountIdAndIdentityType(Long accountId, String identityType);

}