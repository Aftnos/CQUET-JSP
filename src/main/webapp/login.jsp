<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>登录EmarketMall系统</title>
    <meta name="description" content="EmarketMall系统">
    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/style.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/login.min.css" rel="stylesheet"/>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"/>

    <script>
        if (window.top !== window.self) {
            alert('未登录或登录超时。请重新登录');
            window.top.location = window.location
        }
    </script>
</head>
<body class="signin">
<div class="signinpanel">
    <div class="row">
        <div class="col-sm-7">
            <div class="signin-info">
                <div class="m-b"></div>
                <h4>欢迎使用 <strong>后台管理系统</strong></h4>
                <ul class="m-b">
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> SpringBoot</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Mybatis</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Shiro</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Thymeleaf</li>
                    <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> Bootstrap</li>
                </ul>
                <strong>还没有账号？<a href="${ctx}/register.jsp">立即注册&raquo;</a></strong>
            </div>
        </div>
        <div class="col-sm-5">
            <form id="signupForm" autocomplete="off" >
                <h4 class="no-margins">登录：</h4>
                <p class="m-t-md">When we let go of something, it opens up a little space to grow.<br/>学会放手的同时也学会成长。</p>
                <input type="text" name="loginName" class="form-control uname" placeholder="用户名" value="san"/>
                <input type="password" name="password" class="form-control pword" placeholder="密码" value="123456"/>
                <br/>
                <div class="checkbox-custom">
                    <input type="checkbox" id="rememberme" name="rememberme"> <label for="rememberme">记住我</label>
                </div>
                <button class="btn btn-success btn-block" id="btnSubmit" onclick="login()" data-loading="正在验证登录，请稍后...">登录</button>
            </form>
        </div>
    </div>
    <div class="signup-footer">
        <div class="pull-left">
            &copy; 2022 All Rights Reserved. <br>
        </div>
    </div>
</div>
<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<!-- 验证插件 -->
<script src="${ctx}/static/js/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/validate/messages_zh.min.js"></script>
<script src="${ctx}/static/js/login.js"></script>

<script type="text/javascript">
    function login() {
        var loginName = $("input[name='loginName']").val();
        var password = $("input[name='password']").val();
        var rememberMe = $("input[name='rememberme']").is(':checked');
        $.ajax({
            type: "post",
            url: "/login",
            data: {
                "loginName": loginName,
                "password": password,
                "rememberMe": rememberMe
            },
            dataType: "json",
            success: function (ret) {
                if (ret.code == 0) {
                    location.href = '/index.jsp';
                } else {
                    $.modal.alertWarning(ret.msg);
                }
            }
        });
    }
</script>
</body>
</html>
