<%@ page import="org.example.emarketmall.entity.UserInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EmarketMall系统">
    <title>用户管理-详情</title>
    <script type="text/javascript" src="../static/js/common/common-css.js"></script>

    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <%-- 建议使用EL方式或者把变量保存到pageContext中的方式读取保存在request和session中的变量 --%>
    <% pageContext.setAttribute("user", request.getAttribute("user")); %>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
</head>
<body class="white-bg">
<div class="wrapper wrapper-content animated fadeInRight ibox-content">
    <form class="form-horizontal m" id="form-user-edit">
        <input name="id" type="hidden" value="${user.id}">
        <div class="form-group">
            <label class="col-sm-3 control-label is-required">姓名：</label>
            <div class="col-sm-8">
                <input name="name" class="form-control" value="${user.name}" type="text" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label is-required">登录名：</label>
            <div class="col-sm-8">
                <input name="loginName" class="form-control" value="${user.loginName}" type="text" readonly>
            </div>
        </div>
        <!-- 密码经过MD5加密，需要重新设置，无法在个人信息中修改 -->
        <div class="form-group">
            <label class="col-sm-3 control-label">联系电话：</label>
            <div class="col-sm-8">
                <input name="phone" class="form-control" value="${user.phone}" type="text" readonly>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">电子邮箱：</label>
            <div class="col-sm-8">
                <input name="email" class="form-control" value="${user.email}" type="text" readonly>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="../static/js/common/common-js.js"></script>
</body>
</html>
