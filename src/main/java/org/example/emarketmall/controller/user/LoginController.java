package org.example.emarketmall.controller.user;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.example.emarketmall.common.AjaxResult;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.UserInfoService;
import org.example.emarketmall.service.user.impl.UserInfoServiceImpl;
import org.example.emarketmall.service.user.login.LoginService;
import org.example.emarketmall.utils.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PushbackReader;
import java.util.Iterator;
import java.util.Map;

/**
 * @Description: 用户登录控制器
 * @author: april
 * @date: 2022年06月08日 23:17
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    private void doProcessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserInfo userInfo = ServletUtils.getObjectFromPayload(req, UserInfo.class);

        if (userInfo != null && userInfo.getLoginName() != null) {
            ServletUtils.renderString(resp, JSON.toJSONString(ajaxLogin(req, userInfo.getLoginName(), userInfo.getPassword(), false)));
        } else {
            resp.sendRedirect("/login.jsp");
        }
    }

    private AjaxResult ajaxLogin(HttpServletRequest req, String loginName, String password, Boolean rememberMe) {
        UserInfo userInfo = null;
        if (!rememberMe) {
            userInfo = loginService.login(loginName, password);
            if (userInfo != null) {
                req.getSession().setAttribute("loginName", loginName);
                return AjaxResult.success("登录成功");
            }
        }
        return AjaxResult.error("用户或密码错误");
    }
}
