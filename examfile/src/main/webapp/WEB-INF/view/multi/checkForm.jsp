<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>확인 폼</title>
</head>
<body>
	<table>
		<tr>
			<td>제목 : </td>
			<td> ${title}</td>
		</tr>
		<tr>
			<td>내용 : </td>
			<td>${content}</td>
		</tr>
		<tr>
		<c:forEach items="${upFiles}" var="fName">
		<input type="checkbox" name="selectFile" /> ${fName}<br>
		</c:forEach>
		</tr>
	</table>
</body>
</html>