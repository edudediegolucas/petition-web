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
<h2>You're petition has been saved!</h2>
<h3>Petition ID: <s:property value="petitionBean.id"/></h3>
<div style="margin: 0 auto">
    <a href="<s:url action='menu'/>">Menu</a>
</div>
</body>
</html>