<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 폼</title>
<script type="text/javascript">
function writesave(){
	if(document.updateForm.title.value == ""){
		alert("제목을 입력하세요.");
		document.updateForm.title.focus();
		return false;
	}
	if(document.updateForm.inputPass.value == ""){
		alert("비밀번호를 입력하세요.");
		document.updateForm.inputPass.focus();
		return false;
	}
	if(document.updateForm.content.value == ""){
		alert("내용을 입력하세요.");
		document.updateForm.content.focus();
		return false;
	}
}
</script>
</head>
<body style="text-align: center;">
<article>
<section>
	<c:set var="check" value="${empty check? false : check}" />
	<c:if test="${check == false}">
	<form method="post">
	<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="text" name="title" value="${boardDto.title}" size="25px"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
		<a href="${pageContext.request.contextPath}/board/fileOpen?num=${boardDto.num}" target="_blank">${boardDto.file}</a>
			<input type="button" value="파일 삭제" onClick="javascript:location.href='${pageContext.request.contextPath}/board/fileDel?num=${boardDto.num}'">
			<input type="hidden" name="file" value="${boardDto.file}">
			<input type="hidden" name="num" value="${boardDto.num}">
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
	</c:if>
	
	<c:if test="${check == true}">
	<form action="${pageContext.request.contextPath}/board/update" method="post" onsubmit="return writesave()" enctype="multipart/form-data">
	<input type="hidden" value="${num}" name="num">
	<table border="1" style="margin: auto;">
	<tr>
		<td>제목</td>
		<td colspan="3" style="text-align: left; text-indent: 10px"><input type="text" name="title" value="${boardDto.title}" size="25px"></td>
	</tr>
	<tr>
		<td>첨부파일</td>
		<td colspan="3" style="text-align: left; text-indent: 10px">
			<input type="file" name="file" onchange="${boardDto.file}">
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
	</c:if>
</section>
</article>
</body>
</html>