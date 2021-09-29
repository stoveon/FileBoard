<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 게시판 목록</title>
</head>
<body style="text-align: center;">
<c:if test="${!empty message}">
<c:set var="message" value="${msg-noneWord}"/>
	<script type="text/javascript">
	var message = ${message};
	alert(message)
	</script>
</c:if>
	<h2>파일 게시판</h2>
	<h4>(전체 글 : ${totalCount})</h4>
	<article>
		<section>
			<c:if test="${totalCount == 0}">
				<table border="1" style="margin: auto;">
					<tr>
						<td>등록된 글이 없습니다.</td>
					</tr>
				</table>

			</c:if>
			<c:if test="${totalCount > 0}">
			<div>
			<c:if test="">
			<%-- <c:set var="searchUrl" value="${empty searchBox ? '' : 'searchType=' += searchType += '&searchBox=' += searchBox}" /> --%>
			<c:when test="${!empty search.searchBox}">
			<c:set var="searchUrl" value="searchType=${search.searchType}&searchBox=${search.searchBox}&" />			
			<input type="hidden" name="searchUrl" value="${searchUrl}" /> 
			</c:when>
			<c:otherwise>
			<c:set var="searchUrl" value="" />
			</c:otherwise>
			</c:if>
			<form action="<c:url value="/board/list"/>">
				<select id="searchType" name="searchType" size="1">
					<option value="searchTotal" <c:if test="${search.searchType eq 'searchTotal'}">selected</c:if>>전체</option>
					<option value="searchTitle" <c:if test="${search.searchType eq 'searchTitle'}">selected</c:if>>제목</option>
					<option value="searchContent" <c:if test="${search.searchType eq 'searchContent'}">selected</c:if>>내용</option>
				</select>
				<input type="search" name="searchBox" value="${search.searchBox}"/>&nbsp;
				<input type="submit" value="검색" />
			</form>
			</div><br>
				<table id="listTable" border="1" style="margin: auto;">
					<tr>
						<th id="num" name="num">번호</th>
						<th id="title" name="title">제목</th>
						<th id="writer" name="writer">글쓴이</th>
						<th id="regdate" name="regdate">작성일</th>
						<th id="readcount" name="readcount">조회수</th>
					</tr>
				<c:if test="${!empty searchBox}">
					<c:forEach var="boardDto" items="${articleList}" begin="${startNum}" end="${endNum}">
						<tr>
							<td><c:out value="${totalCount - boardDto.rnum+1}"/> </td>
							<td><a href="<c:url value="/board/detail/${boardDto.num}" />">${boardDto.title}</a></td>
							<td>${boardDto.writer}</td>
							<td><fmt:formatDate value="${boardDto.regdate}" pattern="yyyy-MM-dd" /></td>
							<td>${boardDto.readcount}</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty searchBox}">
					<c:forEach var="boardDto" items="${articleList}" >
						<tr>
							<td><c:out value="${totalCount - boardDto.rnum+1}"/> </td>
							<td><a href="<c:url value="/board/detail/${boardDto.num}" />">${boardDto.title}</a></td>
							<td>${boardDto.writer}</td>
							<td><fmt:formatDate value="${boardDto.regdate}" pattern="yyyy-MM-dd" /></td>
							<td>${boardDto.readcount}</td>
						</tr>
					</c:forEach>
				</c:if>
				</table>

				<div style="margin: 10px;">
				<c:if test="${pageNum == startPaging}">
				<button class="btn-prev" onclick="alert('이전 페이지가 없습니다.');">이전</button>
				</c:if>
					<c:if test="${pageNum > startPaging}">
						<button class="btn-prev"
							onclick="<c:url value="/board/list?${searchUrl}pageNum=${pageNum-1}" />">이전</button>
					</c:if>

					<c:forEach var="i" begin="${startPaging}" end="${endPaging}">
						<a href="<c:url value="/board/list?${searchUrl}pageNum=${i}"/>">[${i}]</a>
					</c:forEach>


					<c:if test="${endPaging <= pageNum}">
						<button class="btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</button>
					</c:if>
					<c:if test="${endPaging > pageNum}">
						<button class="btn-next"
							onclick="<c:url value="/board/list?${searchUrl}pageNum=${pageNum+1}" />">다음</button>
					</c:if>
				</div>
			</c:if>
			<div>
			 	<%-- <input type="button" value="index로 이동" onClick="location.href='<c:url value="/index"/>'" />  --%>
					<input type="button" value="글쓰기" onClick="location.href='<c:url value="/board/write"/>'" />
			</div>
		</section>
	</article>
</body>
</html>