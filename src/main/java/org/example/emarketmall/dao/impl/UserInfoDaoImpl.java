package org.example.emarketmall.dao.impl;

import cqcet.aibd.soft.ObjectUtil;
import org.example.emarketmall.dao.IDataAccess;
import org.example.emarketmall.dao.UserInfoDao;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.utils.BeanUtils;
import org.example.emarketmall.utils.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年05月30日 22:43
 */
public class UserInfoDaoImpl implements UserInfoDao {

    private IDataAccess dac;

    public UserInfoDaoImpl() {
        dac = new DataAccessImpl();
    }

    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) throws Exception {
        if (userInfo != null) {
            return selectUserInfoByUserParams(userInfo);
        }
        return dac.getList(UserInfo.class);
    }

    /**
     * 通过userinfo对象中包含的属性查询用户，能实现查询的属性id，loginName，phone，email
     *
     * @param userInfo
     * @return
     */
    private List<UserInfo> selectUserInfoByUserParams(UserInfo userInfo) throws Exception {
        List<UserInfo> userInfoList = new ArrayList<>();
        //是否输入了多个查询条件
        List<String> params = Arrays.asList("id", "name", "loginName", "email", "phone");

        for (String p : params) {
            switch (p) {
                case "id":
                    if (userInfo.getId() != null) {
                        userInfoList.add(selectUserInfoById(userInfo.getId()));
                    }
                    break;
                case "loginName":
                    if (StringUtils.isNotEmpty(userInfo.getLoginName())) {
                        userInfoList.add(selectUserInfoByLoginName(userInfo.getLoginName()));

                    }
                    break;
                case "phone":
                    if (StringUtils.isNotEmpty(userInfo.getPhone())) {
                        userInfoList.add(selectUserInfoByPhone(userInfo.getPhone()));
                    }
                    break;
                case "email":
                    if (StringUtils.isNotEmpty(userInfo.getEmail())) {
                        userInfoList.add(selectUserInfoByEmail(userInfo.getEmail()));
                    }
                    break;
                case "name":
                    if (StringUtils.isNotEmpty(userInfo.getName())) {
                        userInfoList = selectUserInfoByName(userInfo.getName());
                    }
                default:
                    break;
            }
        }
        return userInfoList;
    }

    @Override
    public UserInfo selectUserInfoByLoginName(String loginName) {
        String sql = "select * from user_info where loginName = ? and delFlag= 0";
        return new ObjectUtil<UserInfo>().getOne(sql, UserInfo.class, loginName);
    }

    @Override
    public UserInfo selectUserInfoByPhone(String phone) {
        String sql = "select * from user_info where phone=? and delFlag= 0";
        return new ObjectUtil<UserInfo>().getOne(sql, UserInfo.class, phone);
    }

    @Override
    public UserInfo selectUserInfoByEmail(String email) {
        String sql = "select * from user_info where email = ? and delFlag= 0";
        return new ObjectUtil<UserInfo>().getOne(sql, UserInfo.class, email);
    }

    @Override
    public UserInfo selectUserInfoById(Integer userInfoId) throws Exception {
        return dac.getEntityById(UserInfo.class, userInfoId);
    }

    @Override
    public UserInfo selectUserInfoByIdIncDel(Integer id) {
        String sql = "select * from user_info where id=?";
        return new ObjectUtil<UserInfo>().getOne(sql, UserInfo.class, id);
    }

    @Override
    public List<UserInfo> selectUserInfoByName(String name) {
        String sql = "select * from user_info WHERE name like ? and delFlag= 0";
        return new ObjectUtil<UserInfo>().getList(sql, UserInfo.class, "%" + name + "%");
    }

    @Override
    public int deleteUserInfoById(Integer userInfoId) {
        //软删，修改delFlag字段
        String sql = "update user_info set delFlag=0 where id=?";
        return new ObjectUtil<UserInfo>().update(sql, userInfoId);
    }

    @Override
    public int updateUserInfo(Integer id, UserInfo userInfo) {
        if (userInfo == null) {
            return 0;
        }
        //更新的数据和set后面的字段名一一对应
        String sql = "update user_info set name=?,loginName=?,password=?,phone=?,email=?,avatar=?," +
                "createBy=?,createTime=?,updateBy=?,updateTime=?,delFlag=?,remark=? where id=?";
        return new ObjectUtil<UserInfo>().update(sql, userInfo.getName(), userInfo.getLoginName(), userInfo.getPassword(), userInfo.getPhone(), userInfo.getEmail(), userInfo.getAvatar(),
                userInfo.getCreateBy(), userInfo.getCreateTime(), userInfo.getUpdateBy(), userInfo.getUpdateTime(), userInfo.getDelFlag(), userInfo.getRemark(), id);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        //user_info　13个字段,填写12个,id自动
        String sql = "insert into user_info(`name`,`loginName`,`password`,`phone`,`email`,`avatar`,`createBy`,`createTime`,`updateBy`,`updateTime`,`delFlag`,`remark`) value(?,?,?,?,?,?,?,?,?,?,?,?)";
        if (userInfo == null) {
            return 0;
        }
        return new ObjectUtil<UserInfo>().add(sql, userInfo.getName(), userInfo.getLoginName(), userInfo.getPassword(),
                userInfo.getPhone(), userInfo.getEmail(), userInfo.getAvatar(),
                userInfo.getCreateBy(), userInfo.getCreateTime(), userInfo.getUpdateBy(),
                userInfo.getUpdateTime(), userInfo.getDelFlag(), userInfo.getRemark());
    }

    @Override
    public int checkLoginNameUnique(String loginName) {
        //dbutils的query方法传入的T 类型参数需要空构造方法，否则报空指针
        String sql = "select * from user_info where loginName= ?";
        List<UserInfo> users = new ObjectUtil<UserInfo>().getList(sql, UserInfo.class, loginName);
        return users.size();
    }

    @Override
    public UserInfo checkPhoneUnique(String phonenumber) {
        return null;
    }

    @Override
    public UserInfo checkEmailUnique(String email) {
        return null;
    }
}
