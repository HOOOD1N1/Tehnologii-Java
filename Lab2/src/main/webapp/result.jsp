<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="lab2.classes.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
<table>
	<tr>
		<td>Categories</td>
		<td>Keys</td>
		<td>Values</td>
	</tr>
	<% DataManager data1 = (DataManager)request.getAttribute("data");
	for(int i = 1;i <= data1.getValues().size(); i++){
		System.out.println("Yes1" + data1.getKeys().get(i -1));
		System.out.println("Yes2" + data1.getValues().get(i -1));
		System.out.println("Yes3" + data1.getChosenCategories().get(i -1));
	}
		
	%>
	<% 
	for(int i = 0; i <= data1.getKeys().size();i++) { %>
	<tr>
		<td>${data1.getChosenCategories().get(i-1)}</td>
		<td>${data1.getKeys().get(i-1)}</td>
		<td>${data1.getValues().get(i-1)}</td>
	</tr>
	<%} %>
</table>
</body>
</html>