<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - Petition</title>
</head>
<body style="text-align: center">
<h1>Petition</h1>
<h2>Please <s:property value="userBean.login"/>, introduce your petition!</h2>
<s:form action="savePetition" style="margin:0 auto">
    <s:textfield name="petitionBean.name" type="text" label="Name"/>
    <s:textarea name="petitionBean.data" type="text" label="Petition"/>
    <s:submit value="Save"/>
    <s:reset/>
</s:form>
<div style="margin: 0 auto">
    <a href="<s:url action='menu'/>">Menu</a>
</div>
</body>
</html>