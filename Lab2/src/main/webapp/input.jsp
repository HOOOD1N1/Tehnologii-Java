<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> 
<%@ page import="lab2.classes.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Input</title>
</head>
<body>
<%!
Category categoryList = new Category();
%>
<%
categoryList.setCategories("Word");
categoryList.setCategories("Idiom");
categoryList.setCategories("Expression");

%>

<form action="controller" method="post">

<select name="category" id="category">
    <c:forEach items="${categoryList}" var="category">
        <option value="${category}">${category}</option>
    </c:forEach>
</select>

<br/>
<label for="key">Key</label>
<input type="text" name="key" id="key"/>

<br/>
<label for="value">Value</label>
<input type="text" name="value" id="value"/>

<br/>
<input type="submit" />
</form>

</body>
</html>