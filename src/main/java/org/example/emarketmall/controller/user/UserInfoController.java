package org.example.emarketmall.controller.user;

import com.alibaba.fastjson.JSON;
import org.example.emarketmall.common.AjaxResult;
import org.example.emarketmall.common.BaseException;
import org.example.emarketmall.common.TableDataInfo;
import org.example.emarketmall.entity.UserInfo;
import org.example.emarketmall.service.user.UserInfoService;
import org.example.emarketmall.service.user.impl.UserInfoServiceImpl;
import org.example.emarketmall.utils.BeanUtils;
import org.example.emarketmall.utils.LogUtils;
import org.example.emarketmall.utils.ServletUtils;
import org.example.emarketmall.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: TODO
 * @author: april
 * @date: 2022年06月08日 23:05
 */
@WebServlet("/mall/user")
public class UserInfoController extends HttpServlet {
    private UserInfoService userInfoService = new UserInfoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcessRequest(req, resp);
    }

    private void doProcessRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String referer = req.getHeader("Referer");
        if (referer.contains("index")) {
            resp.sendRedirect("/user/user.jsp");
            return;
        }
        String opt = ServletUtils.getParamFromPayLoad(req, "opt");
        //id可能为null
        String id = ServletUtils.getParamFromPayLoad(req, "id");

        if (StringUtils.isEmpty(opt)) {
            ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("操作选项opt内容异常")));
        }

        UserInfo userInfo = ServletUtils.getObjectFromPayload(req, UserInfo.class);
        if (BeanUtils.isEmpty(userInfo)) {
            userInfo = null;
        }

        switch (opt) {
            case "add":
                //同edit,先跳转到add.jsp页面，在用post提交user信息
                //跳转到add.jsp
                if ("GET".equalsIgnoreCase(req.getMethod())) {
                    resp.sendRedirect("/user/add.jsp");
                    return;
                }
                if ("POST".equalsIgnoreCase(req.getMethod())) {
                    if (userInfo != null) {
                        ServletUtils.renderString(resp, JSON.toJSONString(addUser(userInfo)));
                    } else {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("新增用户信息对象内容异常")));
                    }
                }
                break;
            case "edit":
                //先接收get请求的跳转，打开edit.jsp,此时id不为空，userInfo可以为空
                if ("GET".equalsIgnoreCase(req.getMethod())) {
                    if (StringUtils.isEmpty(id)) {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("编辑ID内容异常")));
                        return;
                    } else {
                        UserInfo user = userInfoService.selectUserInfoById(Integer.parseInt(id));
                        req.setAttribute("user", user);
                        req.getRequestDispatcher("/user/edit.jsp").forward(req, resp);
                        return;
                    }
                }
                //在edit.jsp页修改后提交post请求
                if ("POST".equalsIgnoreCase(req.getMethod())) {
                    if (userInfo != null) {
                        //userInfo.setId(Integer.parseInt(id));
                        ServletUtils.renderString(resp, JSON.toJSONString(editUser(id, userInfo)));
                    } else {
                        ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("编辑用户信息对象内容异常")));
                    }
                }
                break;
            case "remove":
                ServletUtils.renderString(resp, JSON.toJSONString(removeUser(id)));

                break;
            case "list":
                ServletUtils.renderString(resp, JSON.toJSONString(list(userInfo) != null ? list(userInfo) : AjaxResult.error("用户信息查询异常")));
                break;
            case "detail":
                if (StringUtils.isEmpty(id)) {
                    ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("详情ID内容异常")));
                    return;
                } else {
                    UserInfo user = userInfoService.selectUserInfoById(Integer.parseInt(id));
                    req.setAttribute("user", user);
                    req.getRequestDispatcher("/user/detail.jsp").forward(req, resp);
                    return;
                }
            default:
                ServletUtils.renderString(resp, JSON.toJSONString(AjaxResult.error("操作选项opt内容异常")));
        }
    }

    private AjaxResult addUser(UserInfo user) {
        if (user != null) {
            if (userInfoService.registerUserInfo(user)) {
                return AjaxResult.success("新增用户信息成功");
            }
        }
        return AjaxResult.error("新增用户信息失败");
    }

    private AjaxResult editUser(String id, UserInfo user) {
        if (StringUtils.isNotEmpty(id)) {
            UserInfo destUser=userInfoService.selectUserInfoById(Integer.parseInt(id));
            //各位同学可以思考一种更为通用和优雅的方式来实现对目标对象赋值
            destUser.setName(user.getName());
            destUser.setLoginName(user.getLoginName());
            destUser.setPhone(user.getPhone());
            destUser.setEmail(user.getEmail());
            if (userInfoService.updateUserInfo(destUser) > 0) {
                return AjaxResult.success("修改用户信息成功");
            }
        }
        return AjaxResult.error("修改用户信息失败");
    }

    private AjaxResult removeUser(String id) {
        if (StringUtils.isNotEmpty(id)) {
            if (userInfoService.deleteUserInfoById(Integer.parseInt(id)) > 0) {
                return AjaxResult.success("删除用户信息成功");
            }
        }
        return AjaxResult.error("删除用户信息失败");
    }

    private TableDataInfo list(UserInfo user) {
        List<UserInfo> userInfoList = userInfoService.selectUserInfoList(user);
        if (userInfoList != null) {
            return TableDataInfo.getDataTable(userInfoList);
        }
        return null;
    }
}
