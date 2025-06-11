<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="EmarketMall系统">
    <title>用户管理</title>
    <% pageContext.setAttribute("ctx", request.getContextPath());%>
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/style.css" rel="stylesheet"/>
    <!-- bootstrap-table 表格插件样式 -->
    <link href="${ctx}/static/ajax/bootstrap-table/bootstrap-table.min.css?v=20200727" rel="stylesheet"/>
    <link href="${ctx}/static/css/animate.css" rel="stylesheet"/>
    <link href="${ctx}/static/css/style.css?v=20200903" rel="stylesheet"/>
    <link href="${ctx}/static/ajax/easy-ui/easy-ui.css?v=4.5.1" rel="stylesheet"/>
    <%--
    <script type="text/javascript" src="../static/js/common/common-css.js"></script>
    --%>
    <!-- 360浏览器急速模式 -->
    <meta name="renderer" content="webkit">
    <!-- 避免IE使用兼容模式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

</head>

<body class="gray-bg">
<jsp:include page="${ctx}/common/menu.jsp"/>
<div class="mcw">
    <div class="col-md-12">
        <div class="container-div">
            <div class="row">
                <div class="col-sm-12 search-collapse">
                    <form id="formId">
                        <div class="select-list">
                            <ul>
                                <li>
                                    <label>用户名：</label>
                                    <input type="text" name="name" id="name"/>
                                </li>
                                <li>
                                    <label>登录名：</label>
                                    <input type="text" name="loginName" id="loginName"/>
                                </li>
                                <li>
                                    <label>电话：</label>
                                    <input type="text" name="phone" id="phone"/>
                                </li>
                                <li>
                                    <label>邮箱：</label>
                                    <input type="text" name="email" id="email"/>
                                </li>
                                <li>
                                    <a class="btn btn-primary btn-rounded btn-sm" onclick="$.table.search()"><i
                                            class="fa fa-search"></i>&nbsp;搜索</a>
                                    <a class="btn btn-warning btn-rounded btn-sm" onclick="$.form.reset()"><i
                                            class="fa fa-refresh"></i>&nbsp;重置</a>
                                </li>
                            </ul>
                        </div>
                    </form>
                </div>

                <div class="btn-group-sm" id="toolbar" role="group">
                    <a class="btn btn-success" onclick="$.operate.add()">
                        <i class="fa fa-plus"></i> 新增
                    </a>
                    <a class="btn btn-primary single disabled" onclick="$.operate.edit()">
                        <i class="fa fa-edit"></i> 修改
                    </a>
                </div>

                <div class="col-sm-12 select-table table-striped">
                    <table id="bootstrap-table"></table>
                </div>
            </div>
        </div>
    </div>
</div>
<%--<script type="text/javascript" src="../static/js/common/common-js.js"></script>--%>
<!-- 全局js -->
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<!-- bootstrap-table 表格插件 -->
<script src="${ctx}/static/ajax/bootstrap-table/bootstrap-table.min.js?v=20200727"></script>
<script src="${ctx}/static/ajax/bootstrap-table/locale/bootstrap-table-zh-CN.min.js?v=20200727"></script>
<script src="${ctx}/static/ajax/bootstrap-table/extensions/mobile/bootstrap-table-mobile.js?v=20200727"></script>
<!-- 验证插件 -->
<script src="${ctx}/static/js/validate/jquery.validate.min.js"></script>
<script src="${ctx}/static/js/validate/messages_zh.min.js"></script>
<script src="${ctx}/static/js/login.js"></script>
<!-- 遮罩层 -->
<script src="${ctx}/static/ajax/blockUI/jquery.blockUI.js"></script>
<script src="${ctx}/static/ajax/iCheck/icheck.min.js"></script>
<script src="${ctx}/static/ajax/layer/layer.min.js"></script>
<script src="${ctx}/static/ajax/layui/layui.js"></script>
<script src="${ctx}/static/ajax/easy-ui/common.js?v=4.5.1"></script>
<script src="${ctx}/static/ajax/easy-ui/easy-ui.js?v=4.5.1"></script>

<script type="text/javascript">
    let route = "/mall/user";
    let total;
    //方法一，使用集成的bootstrap-table显示和查询，推荐使用方法一
    $(function () {
        let options = {
            id: "bootstrap-table",
            url: route + "?opt=list",
            createUrl: route + "?opt=add",
            removeUrl: route + "?opt=remove&id={id}",
            updateUrl: route + "?opt=edit&id={id}",
            detailUrl: route + "?opt=detail&id={id}",
            toolbar: "toolbar",
            //分页
            sidePagination: "client",
            pagination: true,
            paginationLoop: false,
            pageSize: 3,
            pageList: [10, 25, 50],
            modalName: "用户信息",
            columns: [{
                checkbox: true
            },
                {
                    field: 'id',
                    title: 'ID',
                    visible: false
                },
                {
                    field: 'name',
                    title: '姓名'
                },
                {
                    field: 'loginName',
                    title: '登录名'
                },
                {
                    field: 'phone',
                    title: '联系电话'
                },
                {
                    field: 'email',
                    title: '电子邮箱'
                },
                {
                    field: 'avatar',
                    title: '头像',
                    formatter: function (value) {
                        //通过获取的头像路径，显示图片
                        return $.table.imageView(value);
                    }
                },
                {
                    title: '操作',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var actions = [];
                        actions.push('<a class="btn btn-success btn-xs" href="#" onclick="$.operate.detail(\'' + row.id + '\')"><i class="fa fa-search"></i>详情</a> ');
                        actions.push('<a class="btn btn-warning btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
                        actions.push('<a class="btn btn-danger btn-xs" href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i>删除</a>');
                        return actions.join('');
                    }
                }]
        };
        $.table.init(options);
    });

    // //方法二：使用自定义ajax请求方式，获取user列表信息
    // function queryUserList() {
    //     let userList;
    //     let name = $("#name").val();
    //     let loginName = $("#loginName").val();
    //     let phone = $("#phone").val();
    //     let email = $("#email").val();
    //
    //     $.ajax({
    //         //添加opt=list作为代控制器servlet的动作选项
    //         url: route + "?opt=list",
    //         type: "POST",
    //         dataType: "json",
    //         contentType: "application/json",
    //         data: {
    //             'name': name,
    //             'loginName': loginName,
    //             'phone': phone,
    //             'email': email
    //         },
    //         error: function (result) {
    //             console.log("error_result------------------", result.msg);
    //             if (result.msg == null) {
    //                 result.msg = "获取数据失败";
    //             }
    //             $.modal.alertError(result.msg);
    //         },
    //         success: function (result) {
    //             console.log("success_result------------------", result.total);
    //             userList = result.rows;
    //             total = result.total;
    //         }
    //     });
    //     if (userList != null) {
    //         showTable(userList);
    //         $('#bootstrap-table').bootstrapTable('load', userList);
    //     }
    // }
    //
    // //使用本地数据源userList在bootstrap table中显示user列表
    // function showTable(list) {
    //     let options = {
    //         id: "bootstrap-table",
    //         data: list,
    //         //url:
    //         createUrl: route + "/add.jsp",
    //         removeUrl: route + "/remove.jsp?id={id}",
    //         updateUrl: route + "/edit.jsp?id={id}",
    //         toolbar: "toolbar",
    //         modalName: "用户信息",
    //         columns: [{
    //             checkbox: true
    //         },
    //             {
    //                 field: 'id',
    //                 title: 'ID',
    //                 visible: false
    //             },
    //             {
    //                 field: 'name',
    //                 title: '姓名'
    //             },
    //             {
    //                 field: 'loginName',
    //                 title: '登录名'
    //             },
    //             {
    //                 field: 'phone',
    //                 title: '联系电话'
    //             },
    //             {
    //                 field: 'email',
    //                 title: '电子邮箱'
    //             },
    //             {
    //                 field: 'avatar',
    //                 title: '头像',
    //                 formatter: function (value) {
    //                     //通过获取的头像路径，显示图片
    //                     return $.table.imageView(value);
    //                 }
    //             },
    //             {
    //                 title: '操作',
    //                 align: 'center',
    //                 formatter: function (value, row, index) {
    //                     var actions = [];
    //                     actions.push('<a class="btn btn-success btn-xs" href="#" onclick="$.operate.edit(\'' + row.id + '\')"><i class="fa fa-edit"></i>编辑</a> ');
    //                     actions.push('<a class="btn btn-danger btn-xs" href="#" onclick="$.operate.remove(\'' + row.id + '\')"><i class="fa fa-remove"></i>删除</a>');
    //                     return actions.join('');
    //                 }
    //             }]
    //     };
    //     $.table.init(options);
    // }
</script>
</body>
</html>