<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Jackson
  Date: 2020/7/16
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--要求当前登录用户必须有日志管理的权限，才显示--%>
<shiro:hasPermission name="日志管理"><a href="#" >日志管理</a></shiro:hasPermission>
<shiro:hasPermission name="用户管理"><a href="#" >用户管理</a></shiro:hasPermission>
<shiro:hasPermission name="角色管理"><a href="#" >角色管理</a></shiro:hasPermission>

</body>
</html>
