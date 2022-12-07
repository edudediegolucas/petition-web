<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - Login</title>
</head>
<body style="text-align: center">
<h1>Petition Web</h1>
<s:form action="login" style="margin:0 auto">
    <s:textfield name="loginBean.login" type="text" label="login"/>
    <s:password name="loginBean.password" label="password"/>
    </br>
    <s:submit value="Log in"/>
    <s:reset/>
</s:form>
</body>
</html>