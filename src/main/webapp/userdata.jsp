<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - User Data</title>
</head>
<body style="text-align: center">
<h1>User data</h1>
<h2>Your info <s:property value="userBean.login"/></h2>
<div style="margin: 0 auto">
    <p>Login: <s:property value="userBean.login"/></p>
    <p>Name: <s:property value="userBean.name"/></p>
    <p>Email: <s:property value="userBean.email"/></p>
    <p>Creation Time: <s:date name="userBean.creationTime" format="dd/MM/yyyy hh:mm:ss"/></p>
    <p>Last Access Time: <s:date name="userBean.lastAccessTime" nice="true"/></p>
</div>
<div style="margin: 0 auto">
    <s:iterator value="listPetitions">
        <div style="border-style: solid">
            <p>ID: <s:property value="id"/> | Name: <s:property value="name"/></p>
            <p>Data: <s:property value="data"/></p>
        </div>
    </s:iterator>
</div>

<div style="margin: 0 auto">
    <a href="<s:url action='menu'/>">Menu</a>
</div>
</body>
</html>
