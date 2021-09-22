<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 폼</title>
</head>
<body style="text-align: center;">
<article>
<section>
	<form action="<c:url value="/board/update"/>" method="post" enctype="multipart/form-data">
	<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="text" name="title" value="${boardDto.title}" size="25px"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
			<c:set var="check" value="${empty check? false : check}" />
			<c:if test="${check == false}">
				<a href="<c:url value="/board/fileDown/${boardDto.num}"/>">${boardDto.file}</a>
				<input type="button" value="파일 삭제" onClick="<c:url value="/board/fileDel?num=${boardDto.num}"/>"/>
			</c:if>
			<c:if test="${check == true}">
				<input type="file" name="file" />
			</c:if>
			<input type="hidden" name="file" value="${boardDto.file}"/>
			<input type="hidden" name="num" value="${boardDto.num}"/>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="password" name="checkPass" size="25px"></td>
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
		<input type="submit" value="수정">&nbsp;&nbsp;
		<input type="button" value="목록" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'">
	</td>
	</tr>
	</table>	
	</form>
</section>
</article>
</body>
</html>