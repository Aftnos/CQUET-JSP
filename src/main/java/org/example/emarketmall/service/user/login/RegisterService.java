package org.example.emarketmall.service.user.login;


import org.example.emarketmall.common.AsyncManager;
import org.example.emarketmall.common.constant.AsyncFactory;
import org.example.emarketmall.common.constant.Constants;
import org.example.emarketmall.common.constant.ShiroConstants;
import org.example.emarketmall.common.constant.UserConstants;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.UserInfoService;
import org.example.emarketmall.service.user.impl.UserInfoServiceImpl;
import org.example.emarketmall.utils.DateUtils;
import org.example.emarketmall.utils.LogUtils;
import org.example.emarketmall.utils.ServletUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 注册校验方法
 */
@Component
public class RegisterService {
    private UserInfoService userInfoService;

    public RegisterService() {
        userInfoService = new UserInfoServiceImpl();
    }

    public RegisterService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    /**
     * 注册
     */
    public String register(UserInfo user) {
        String msg = "", loginName = user.getLoginName(), password = user.getPassword(), phone = user.getPhone();

        if (StringUtils.isEmpty(loginName)) {
            msg = "用户名不能为空";
        }
        if (StringUtils.isEmpty(password)) {
            msg = "用户密码不能为空";
        }
        if (StringUtils.isEmpty(phone)) {
            msg = "联系电话不能为空";
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        }
        if (loginName.length() < UserConstants.USERNAME_MIN_LENGTH
                || loginName.length() > UserConstants.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        }
        if (!phone.matches(UserConstants.MOBILE_PHONE_NUMBER_PATTERN)) {
            msg = "请输入正确的手机号码";
        }
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userInfoService.checkLoginNameUnique(loginName))) {
            msg = "保存用户'" + loginName + "'失败，注册账号已存在";
        } else {
            user.setCreateTime(DateUtils.getTime());
            user.setCreateBy(loginName);
            boolean regFlag = userInfoService.registerUserInfo(user);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                msg = "注册成功";
            }
        }
        return msg;
    }
}
