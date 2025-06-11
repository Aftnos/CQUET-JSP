<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="org.example.emarketmall.dao.ProductInfoDao" %>
<%@ page import="org.example.emarketmall.dao.impl.ProductInfoDaoImp" %>
<%@ page import="org.example.emarketmall.entity.ProductInfo" %>
<%
    request.setCharacterEncoding("UTF-8");
    ProductInfoDao productDao = new ProductInfoDaoImp();
    List<ProductInfo> productList = new ArrayList<>();
    try {
        productList = productDao.selectProductInfoList(null);
    } catch (Exception e) {
        // 数据库可能未初始化，忽略异常
    }
%>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>EmarketMall - 商城首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <% pageContext.setAttribute("ctx", request.getContextPath()); %>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/style.css"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${ctx}/mall/index.jsp">EmarketMall</a>
    <div class="ml-auto">
        <a href="${ctx}/login.jsp" class="btn btn-outline-primary">后台管理</a>
    </div>
</nav>
<div class="container mt-4">
    <h3 class="mb-3">商品列表</h3>
    <div class="row">
        <% if (productList != null && !productList.isEmpty()) { %>
            <% for(ProductInfo p : productList) { %>
                <div class="col-md-3">
                    <div class="card mb-4">
                        <img class="card-img-top" src="${ctx}/static/img/blue.png" alt="商品图">
                        <div class="card-body">
                            <h5 class="card-title"><%=p.getProductName()%></h5>
                            <p class="card-text">价格：￥<%=p.getPrice()%></p>
                        </div>
                    </div>
                </div>
            <% } %>
        <% } else { %>
            <p class="text-muted">暂无商品信息</p>
        <% } %>
    </div>
</div>
<script src="${ctx}/static/js/jquery.min.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
</body>
</html>
