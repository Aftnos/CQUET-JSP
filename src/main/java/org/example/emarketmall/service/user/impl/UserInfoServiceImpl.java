package org.example.emarketmall.service.user.impl;

import org.example.emarketmall.service.user.login.PasswordService;
import org.example.emarketmall.common.constant.UserConstants;
import org.example.emarketmall.dao.UserInfoDao;
import org.example.emarketmall.dao.impl.UserInfoDaoImpl;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.UserInfoService;
import org.example.emarketmall.utils.StringUtils;

import java.util.List;

/**
 * @Description: 用户管理Service
 * @author: april
 * @date: 2022年06月06日 12:19
 */
public class UserInfoServiceImpl implements UserInfoService {
    private UserInfoDao userInfoDao;

    public UserInfoServiceImpl() {
        userInfoDao = new UserInfoDaoImpl();
    }

    @Override
    public List<UserInfo> selectUserInfoList(UserInfo userInfo) {
        try {
            return userInfoDao.selectUserInfoList(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserInfo selectUserInfoByLoginName(String loginName) {
        return userInfoDao.selectUserInfoByLoginName(loginName);
    }

    @Override
    public UserInfo selectUserInfoByPhoneNumber(String phoneNumber) {
        return userInfoDao.selectUserInfoByPhone(phoneNumber);
    }

    @Override
    public UserInfo selectUserInfoByEmail(String email) {
        return userInfoDao.selectUserInfoByEmail(email);
    }

    @Override
    public UserInfo selectUserInfoById(Integer id) {
        try {
            return userInfoDao.selectUserInfoById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUserInfoById(Integer id) {
        return userInfoDao.deleteUserInfoById(id);
    }

    @Override
    public int insertUserInfo(UserInfo userInfo) {
        if (StringUtils.isEmpty(userInfo.getCreateBy())) {
            userInfo.setCreateBy("admin");
        }
        return userInfoDao.insertUserInfo(userInfo);
    }

    @Override
    public boolean registerUserInfo(UserInfo userInfo) {
        String loginName = userInfo.getLoginName();
        String password = userInfo.getPassword();
        if (StringUtils.isEmpty(userInfo.getName())) {
            userInfo.setName(loginName);
        }
        userInfo.setPassword(new PasswordService().encryptPassword(loginName, password));
        int bl = userInfoDao.insertUserInfo(userInfo);
        if (bl == 1) {
            return true;
        }
        //防止可能由于线程池异常，会意外新增2条相同记录
        if (bl > 1) {
            if (UserConstants.USER_NAME_NOT_UNIQUE.equalsIgnoreCase(checkLoginNameUnique(userInfo.getLoginName()))) {
                userInfoDao.deleteUserInfoById(userInfo.getId());
            }
            return true;
        }
        return false;
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        Integer id = userInfo.getId();
        return userInfoDao.updateUserInfo(id, userInfo);
    }

    @Override
    public int resetUserInfoPwd(UserInfo userInfo) {
        return 0;
    }

    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = userInfoDao.checkLoginNameUnique(loginName);
        return count == 0 ? UserConstants.USER_NAME_UNIQUE : UserConstants.USER_NAME_NOT_UNIQUE;
    }

    @Override
    public String checkPhoneUnique(UserInfo userInfo) {
        return null;
    }

    @Override
    public String checkEmailUnique(UserInfo userInfo) {
        return null;
    }
}
