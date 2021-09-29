<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 등록 페이지</title>
</head>
<body style="text-align: center;">
<section>
<!-- <c:url value=""/>와 ${pageContext.request.contextPath}는 같음 -->
<form method="post" enctype="multipart/form-data">
	<input type="hidden" name="num" value="${boardDto.num}">
	<input type="hidden" name="ref" value="${boardDto.ref}">
	<input type="hidden" name="step" value="${boardDto.step}">
	<input type="hidden" name="depth" value="${boardDto.depth}">

<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<c:set var="checkRe" value="${empty checkRe? false : checkRe}"/>
		<c:if test="${checkRe == true}">
			<td colspan="3" style="text-align: left; text-indent: 10px;">
			<input type="text" name="title" value="[답글] " size="25"/></td>
		</c:if>
		<c:if test="${checkRe == false}">
			<td colspan="3" style="text-align: left; text-indent: 10px;">
			<input type="text" name="title" size="25"/></td>
		</c:if>
	</tr>
	<tr>
		<td>작성자</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;">
		<input type="text" name="writer"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;">
		<input type="password" name="pass" placeholder="15자 이내"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px;">
		<input type="file" name="fileUp"></td>
	</tr>
	<tr>
		<td colspan="4"><textarea name="content" cols="50" rows="7" placeholder="내용 입력"></textarea></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="submit" value="등록">
			<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'" value="목록">
		</td>
	</tr>
</table>
</form>
</section>
</body>
</html>