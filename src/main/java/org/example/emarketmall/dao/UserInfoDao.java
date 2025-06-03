package org.example.emarketmall.dao;

import org.example.emarketmall.entity.UserInfo;

import java.util.List;

public interface UserInfoDao {

    /**
     * 根据条件分页查询用户列表
     *
     * @param userInfo 用户信息
     * @return 用户信息集合信息
     */
    List<UserInfo> selectUserInfoList(UserInfo userInfo) throws Exception;


    /**
     * 通过用户名查询用户
     *
     * @param loginName 用户名
     * @return 用户对象信息
     */
    UserInfo selectUserInfoByLoginName(String loginName);

    /**
     * 通过手机号码查询用户
     *
     * @param phone 手机号码
     * @return 用户对象信息
     */
    UserInfo selectUserInfoByPhone(String phone);

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    UserInfo selectUserInfoByEmail(String email);

    /**
     * 通过ID查询用户
     *
     * @param userInfoId 用户ID
     * @return 用户对象信息
     */
    UserInfo selectUserInfoById(Integer userInfoId) throws Exception;

    /**
     * 通过id查询用户，包含软删数据
     * @param id
     * @return
     * @throws Exception
     */
    UserInfo selectUserInfoByIdIncDel(Integer id);

    /**
     * 通过用户名name查询用户信息
     *
     * @param name 用户名
     * @return 用户列表
     */
    List<UserInfo> selectUserInfoByName(String name);

    /**
     * 通过用户ID删除用户
     *
     * @param userInfoId 用户ID
     * @return 结果
     */
    int deleteUserInfoById(Integer userInfoId);

    /**
     * 修改用户信息
     *
     * @param id       用户id
     * @param userInfo 用户信息
     * @return 结果
     */
    int updateUserInfo(Integer id, UserInfo userInfo);

    /**
     * 新增用户信息
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    int insertUserInfo(UserInfo userInfo);

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 登录名称
     * @return 结果
     */
    int checkLoginNameUnique(String loginName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    UserInfo checkPhoneUnique(String phonenumber);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    UserInfo checkEmailUnique(String email);

}
