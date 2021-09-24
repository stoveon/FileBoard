<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<c:set var="message" value="${msg}"/>
<c:if test="${!message eq null}">
<script type="text/javascript">
	var message = ${message};
	alert(message);
</script>
</c:if>
<meta charset="UTF-8">
<title>파일 게시판 상세페이지</title>
</head>
<body style="text-align: center;">
<article>
<section>
<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3">${boardDto.title}</td>
	</tr>
	<tr>
		<td>작성일</td>
		<td colspan="3" style="text-align: lreft; text-indent: 10px;">
		<fmt:formatDate value="${boardDto.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
	</tr>
	<tr>
		<td>작성자</td><td>${boardDto.writer}</td>
		<td>조회수</td><td>${boardDto.readcount}</td>
	</tr>
		<tr>
		<td>첨부 파일</td>
		<td colspan="3"><a href="<c:url value="/board/fileDown/${boardDto.num}"/>">${boardDto.file}</a></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">${boardDto.content}</td>
	</tr>
	<tr>
	<c:if test="${maxNum != boardDto.num || minNum != boardDto.num}">
		<c:set var="size" value="2"/>
	</c:if>
	<c:if test="${maxNum == boardDto.num || minNum == boardDto.num}">
		<c:set var="size" value="4"/>
	</c:if>
		<c:if test="${minNum != boardDto.num}">
		<td colspan="${size}"><a href="${pageContext.request.contextPath}/board/detail/${boardDto.num}?type=before">이전글</a></td>
		</c:if>
		<c:if test="${maxNum != boardDto.num}">
		<td colspan="${size}"><a href="${pageContext.request.contextPath}/board/detail/${boardDto.num}?type=after">다음글</a></td>
		</c:if>
	</tr>
</table>
<div style="margin: 10px;">
		<form method="get" action="${pageContext.request.contextPath}/board/write/${boardDto.num}">
		<input type="hidden" name="num" value="${boardDto.num}">
		<input type="hidden" name="ref" value="${boardDto.ref}">
		<input type="hidden" name="step" value="${boardDto.step}">
		<input type="hidden" name="depth" value="${boardDto.depth}">
		<input type="submit" value="답글">
		
		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/update/${boardDto.num}'" value="수정">

		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/delete/${boardDto.num}'" value="삭제">
		<input type="button" onClick="javascript:location.href='${pageContext.request.contextPath}/board/list'" value="목록">
		</form>
</div>
</section>
</article>
</body>
</html>