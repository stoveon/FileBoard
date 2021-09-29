<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 페이지</title>
</head>
<body style="text-align: auto;">
<h2>회원 정보 입력</h2>
	<form:form action="join2" method="post" commandName="joinCommand">
		<b>아이디</b><br>
		<form:input path="email" />
		<form:errors path="email"/><br><br>
		<b>비밀번호</b><br>
		<form:password path="password" />
		<form:errors path="password"/><br><br>
		<b>비밀번호 확인</b><br>
		<form:password path="passwordCHK" />
		<form:errors path="passwordCHK"/><br><br>
		<b>닉네임</b><br>
		<form:input path="nickName" />
		<form:errors path="nickName"/><br><br>
		<b>생년월일</b><br>
		<form:input path="birthYear" placeholder="년(4자)" />
		<form:errors path="birthYear"/>
		<form:select path="birthMonth"  >
			<form:option value="01" label="1" />
			<form:option value="02">2</form:option>
			<form:option value="03">3</form:option>
			<form:option value="04">4</form:option>
			<form:option value="05">5</form:option>
			<form:option value="06">6</form:option>
			<form:option value="07">7</form:option>
			<form:option value="08">8</form:option>
			<form:option value="09">9</form:option>
			<form:option value="10">10</form:option>
			<form:option value="11">11</form:option>
			<form:option value="12">12</form:option>
		</form:select>
		<form:errors path="birthMonth"/>
		<form:input path="birthDay" placeholder="일" />
		<form:errors path="birthDay"/><br><br>
		<b>휴대전화</b><br>
		<form:input path="phoneNumberOne" />
		<form:errors path="phoneNumberOne"/> - 
		<form:input path="phoneNumberTwo" />
		<form:errors path="phoneNumberTwo"/> - 
		<form:input path="phoneNumberThree" />
		<form:errors path="phoneNumberThree"/><br><br>
		<b>주소</b><br>
		<form:input path="address" /> 
		<input type="submit" value="가입하기" />
	</form:form>
</body>
</html>