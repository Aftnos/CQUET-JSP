<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" %>

<% pageContext.setAttribute("ctx", request.getContextPath());%>
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet"/>
<link href="${ctx}/static/css/menu.css" rel="stylesheet"/>
<link href="${ctx}/static/css/style.css" rel="stylesheet"/>

<!--msb: main sidebar-->
<div class="msb" id="msb">
    <nav class="navbar navbar-default" role="navigation">
        <div class="navbar-header">
            <div class="brand-wrapper">
                <div class="brand-name-wrapper">
                    <a class="navbar-brand" href="${ctx}/index.jsp">
                        EmarketMall系统
                    </a>
                </div>
            </div>
        </div>

        <!-- Main Menu -->
        <div class="side-menu-container">
            <ul class="nav navbar-nav">
                <li><a href="${ctx}/user/user.jsp"><span class="glyphicon glyphicon-user"></span> 用户管理</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-gift"></span> 商品管理</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> 购物车管理</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-align-left"></span> 订单管理</a></li>

                <!-- Dropdown-->
                <li class="panel panel-default" id="dropdown">
                    <a data-toggle="collapse" href="#dropdown-lvl1">
                        <span class="glyphicon glyphicon-paperclip"></span> 二级菜单
                    </a>
                    <!-- Dropdown level 1 -->
                    <div id="dropdown-lvl1" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav navbar-nav">
                                <li><a href="#">子菜单1</a></li>
                                <li><a href="#">子菜单2</a></li>
                                <li><a href="#">子菜单3</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="mnb">
    <div class="navbar navbar-default navbar-fixed-top">
        <div class="col-md-12">
            <div class="navbar-right">
                <div style="padding: 15px 0;">
                    <a href="#" id="msbo"><span class="glyphicon glyphicon-log-out">注销</span></a>
                </div>
            </div>
        </div>
    </div>
</div>


