<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>用户管理-新增</title>
    <meta name="description" content="EmarketMall系统">
    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <script type="text/javascript" src="../static/js/common/common-css.js"></script>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="${ctx}/static/favicon.ico"/>
</head>
<body class="white-bg">
    <div class="wrapper wrapper-content animated fadeInRight ibox-content">
        <form class="form-horizontal m" id="form-student-add">
            <div class="form-group">    
                <label class="col-sm-3 control-label is-required">姓名：</label>
                <div class="col-sm-8">
                    <input name="name" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">    
                <label class="col-sm-3 control-label is-required">登录名：</label>
                <div class="col-sm-8">
                    <input name="loginName" class="form-control" type="text" required>
                </div>
            </div>
            <div class="form-group">    
                <label class="col-sm-3 control-label">密码：</label>
                <div class="col-sm-8">
                    <input name="password" class="form-control" type="password">
                </div>
            </div>
            <div class="form-group">    
                <label class="col-sm-3 control-label">联系电话：</label>
                <div class="col-sm-8">
                    <input name="phone" class="form-control" type="text">
                </div>
            </div>
            <div class="form-group">    
                <label class="col-sm-3 control-label">电子邮箱：</label>
                <div class="col-sm-8">
                    <input name="email" class="form-control" type="text">
                </div>
            </div>

        </form>
    </div>
    <!-- 全局js -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <!-- 验证插件 -->
    <script src="${ctx}/static/js/validate/jquery.validate.min.js"></script>
    <script src="${ctx}/static/js/validate/messages_zh.min.js"></script>
    <!-- bootstrap-table 表格插件 -->
    <script src="${ctx}/static/ajax/bootstrap-table/bootstrap-table.min.js?v=20200727"></script>
    <script src="${ctx}/static/ajax/bootstrap-table/locale/bootstrap-table-zh-CN.min.js?v=20200727"></script>
    <script src="${ctx}/static/ajax/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js?v=20200727"></script>
    <!-- 遮罩层 -->
    <script src="${ctx}/static/ajax/blockUI/jquery.blockUI.js"></script>
    <script src="${ctx}/static/ajax/iCheck/icheck.min.js"></script>
    <script src="${ctx}/static/ajax/layer/layer.min.js"></script>
    <script src="${ctx}/static/ajax/layui/layui.js"></script>
    <script src="${ctx}/static/ajax/easy-ui/common.js?v=4.5.1"></script>
    <script src="${ctx}/static/ajax/easy-ui/easy-ui.js?v=4.5.1"></script>
    <script type="text/javascript">
        var prefix = "/mall/user"
        $("#form-student-add").validate({
            focusCleanup: true
        });

        function submitHandler() {
            if ($.validate.form()) {
                $.operate.save(prefix + "?opt=add", $('#form-student-add').serialize());
            }
        }
    </script>
</body>
</html>