package org.example.emarketmall.service.user.login;

import org.example.emarketmall.common.BaseException;
import org.example.emarketmall.common.constant.Constants;
import org.example.emarketmall.common.constant.ShiroConstants;
import org.example.emarketmall.common.constant.AsyncFactory;
import org.example.emarketmall.common.AsyncManager;
import org.example.emarketmall.common.constant.UserConstants;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.UserInfoService;
import org.example.emarketmall.service.user.impl.UserInfoServiceImpl;
import org.example.emarketmall.utils.LogUtils;
import org.example.emarketmall.utils.ServletUtils;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 */
public class LoginService {

    private PasswordService passwordService;

    private UserInfoService userInfoService;

    public LoginService(){
        passwordService=new PasswordService();
        userInfoService=new UserInfoServiceImpl();
    }

    public LoginService(UserInfoService userInfoService,PasswordService passwordService){
        this.passwordService=passwordService;
        this.userInfoService=userInfoService;
    }

    public LoginService(UserInfoService userInfoService){
        this.userInfoService=userInfoService;
        passwordService=new PasswordService();
    }

    /**
     * 登录
     */
    public UserInfo login(String username, String password) {

        String module = "user_info";
        // 验证码校验 错误
        /*
        if (ShiroConstants.CAPTCHA_ERROR.equals(ServletUtils.getRequest().getAttribute(ShiroConstants.CURRENT_CAPTCHA))) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "user.jcaptcha.error"));
            throw new BaseException(module, LogUtils.message("user.jcaptcha.error"), null);
        }*/
        // 用户名或密码为空 错误
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "user.not.null"));
            throw new BaseException(module, LogUtils.message("user.not.exist"), null);

        }
        // 密码如果不在指定范围内(6,20) 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "user.password.not.match"));
            throw new BaseException(module, LogUtils.message("user.password.not.match"), null);

        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "user.password.not.match"));
            throw new BaseException(module, LogUtils.message("user.password.not.match"), null);

        }

        // 查询用户信息
        UserInfo user = userInfoService.selectUserInfoByLoginName(username);
        if (user == null) {
            //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, "user.not.exists"));
            throw new BaseException(module, LogUtils.message("user.not.exist"), null);
        }
        passwordService.validate(user, password);
        //AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, "user.login.success"));
        return user;
    }


}
