package org.example.emarketmall.service.user.login;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheLoader;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.example.emarketmall.common.BaseException;
import org.example.emarketmall.common.constant.Constants;
import org.example.emarketmall.common.constant.AsyncFactory;
import org.example.emarketmall.common.AsyncManager;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.utils.GuavaCacheUtils;
import org.example.emarketmall.utils.LogUtils;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 登录密码方法
 */
public class PasswordService {

    private Cache<String, Object> loginRecordCache;
    private Integer maxRetryCount;

    public PasswordService() {
        maxRetryCount = 5;

        //loginRecordCache仅仅保存用户当前登录次数
        loginRecordCache = GuavaCacheUtils.initCacheBuilder().build(new CacheLoader<String, Object>() {
            @Override
            public Object load(String key) throws Exception {
                return 0;
            }
        });
    }

    public void validate(UserInfo user, String password) {
        String loginName = user.getLoginName();

        AtomicInteger retryCount = null;
        try {
            retryCount = (AtomicInteger) loginRecordCache.get(loginName, () -> new AtomicInteger(0));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            loginRecordCache.put(loginName, retryCount);
        }
        if (retryCount.incrementAndGet() > maxRetryCount) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, LogUtils.message("user.password.retry.limit.exceed", maxRetryCount)));
            throw new BaseException("user_info", "user.password.retry.limit.exceed", new Object[]{maxRetryCount});
        }

        if (!matches(user, password)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(loginName, Constants.LOGIN_FAIL, LogUtils.message("user.password.retry.limit.count", retryCount)));
            loginRecordCache.put(loginName, retryCount);
            throw new BaseException("user_info", "user.password.retry.limit.count");
        } else {
            clearLoginRecordCache(loginName);
        }
    }

    public boolean matches(UserInfo user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword));
    }

    public void clearLoginRecordCache(String loginName) {
        loginRecordCache.invalidate(loginName);
    }

    public String encryptPassword(String loginName, String password) {
        return new Md5Hash(loginName + password).toHex();
    }
}
