<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${data}</h2><br>
<a href="<c:url value="/board/join"/>" >회원가입</a><br>
<a href="<c:url value="/board/list"/>" >게시판으로 이동</a>