<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - Register</title>
</head>
<body style="text-align: center">
<h1>New user!</h1>
<s:form action="register" style="margin:0 auto">
    <s:textfield name="loginBean.login" type="text" label="login"/>
    <s:textfield name="loginBean.name" type="text" label="name"/>
    <s:textfield name="loginBean.email" type="email" label="email"/>
    <s:password name="loginBean.password" label="password"/>
    <s:submit value="Register me!"/>
    <s:reset/>
</s:form>
</body>
</html>