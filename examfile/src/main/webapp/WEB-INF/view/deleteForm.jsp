<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="msg" value="${msg}"/>
<c:if test="${!empty msg}">
	<script type="text/javascript">
	var message = "${msg}";
	alert(message);
	</script>
</c:if>
<meta charset="UTF-8">
<title>삭제 폼</title>
</head>
<body style="text-align: center;">
<article>
<section>
<form:form method="post" name="deleteForm" commandName="boardDto">
	<form:hidden path="num" value="${boardDto.num}"/>
	<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<form:input path="title" value="${boardDto.title}" size="25"/></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<a href="<c:url value="/board/fileDown/${boardDto.num}"/>">${boardDto.file}</a></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<input type="password" name="checkPass" size="25"></td>
	</tr>
	<tr>
		<td>작성자</td><td>${boardDto.writer}</td>
		<td>조회수</td><td>${boardDto.readcount}</td>
	</tr>
	<tr>
		<td colspan="4"><textarea rows="7" cols="40" name="content">${boardDto.content}</textarea></td>
	</tr>
	<tr>
	<td colspan="4">
		<input type="submit" value="삭제">&nbsp;&nbsp;
		<input type="button" onClick="<c:url value="/board/list"/>" value="목록"/>
	</td>
	</tr>
	</table>
	</form:form>
</section>
</article>
</body>
</html>