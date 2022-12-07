<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - Menu</title>
</head>
<body style="text-align: center">
<h1>Menu</h1>
<h2>Welcome <s:property value="loginBean.login"/></h2>
<div style="margin: 0 auto">
    <p><a href="<s:url action='petition'/>">Create Petition</a></p>
    <p><a href="<s:url action='data'/>">User Data</a></p>
    <p><a href="<s:url action='friends'/>">List Friends</a></p>
    <p><a href="<s:url action='dismiss'/>">Dismiss User</a></p>
    <p><a href="<s:url action='logout'/>">Logout</a></p>
</div>
</body>
</html>
