package org.example.emarketmall.service.user;

import org.example.emarketmall.entity.UserInfo;

import java.util.List;

/**
 * @author Annisia
 */
public interface UserInfoService {
    /**
     * 根据条件分页查询用户列表
     *
     * @param userInfo 用户信息
     * @return 用户信息集合信息
     */
    public List<UserInfo> selectUserInfoList(UserInfo userInfo);

    /**
     * 通过用户名查询用户,同登录
     *
     * @param userInfoName 用户名
     * @return 用户对象信息
     */
    public UserInfo selectUserInfoByLoginName(String userInfoName);

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 手机号码
     * @return 用户对象信息
     */
    public UserInfo selectUserInfoByPhoneNumber(String phoneNumber);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    public UserInfo selectUserInfoByEmail(String email);

    /**
     * 通过用户ID查询用户
     *
     * @param id 用户ID
     * @return 用户对象信息
     */
    public UserInfo selectUserInfoById(Integer id);


    /**
     * 通过用户ID删除用户
     *
     * @param id 用户ID
     * @return 结果
     */
    public int deleteUserInfoById(Integer id);

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public int insertUserInfo(UserInfo userInfo);

    /**
     * 注册用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public boolean registerUserInfo(UserInfo userInfo);

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public int updateUserInfo(UserInfo userInfo);


    /**
     * 修改用户密码信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public int resetUserInfoPwd(UserInfo userInfo);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    public String checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public String checkPhoneUnique(UserInfo userInfo);

    /**
     * 校验email是否唯一
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    public String checkEmailUnique(UserInfo userInfo);
}
