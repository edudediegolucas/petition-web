<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Petition Web - List Friends</title>
</head>
<body style="text-align: center">
<h1>List Friends</h1>
<h2>How many people are there?</h2>
<div style="margin: 0 auto">
    <s:iterator value="listUsers">
        <div style="border-style: solid">
            <p>User: <s:property value="login"/> | Name: <s:property value="name"/> | Email: <s:property
                    value="email"/></p>
        </div>
    </s:iterator>
</div>

<div style="margin: 0 auto">
    <a href="<s:url action='menu'/>">Menu</a>
</div>
</body>
</html>
