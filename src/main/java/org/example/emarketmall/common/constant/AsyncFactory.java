package org.example.emarketmall.common.constant;

import org.example.emarketmall.common.constant.Constants;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.utils.LogUtils;
import org.example.emarketmall.utils.SpringUtils;
import org.example.emarketmall.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.TimerTask;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年06月06日 23:37
 */
public class AsyncFactory {

    private static final Logger userInfoLogger= LoggerFactory.getLogger("user_info");

    /**
     * 记录登录信息
     *
     * @param username 用户名
     * @param status 状态
     * @param message 消息
     * @param args 列表
     * @return 任务task
     */
    public static TimerTask recordLogininfor(final String username, final String status, final String message, final Object... args)
    {
        return new TimerTask()
        {
            @Override
            public void run()
            {
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                userInfoLogger.info(s.toString(), args);
            }
        };
    }
}
