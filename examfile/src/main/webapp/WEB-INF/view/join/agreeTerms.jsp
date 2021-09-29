<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약관동의 페이지</title>
</head>
<body>
<c:if test="${msg == false}">
<script type="text/javascript">
alert("필수 사항을 체크하지 않았습니다.");
</script>
</c:if>
<h2>약관 동의</h2>
<form:form method="post" commandName="agreeCommand">
	<div>
		<label><input type="checkbox" name="agreeAll" value="agreeAll">
		<strong>@@@ 이용약관, 개인정보 수집 및 이용, <br>&nbsp;&nbsp;&nbsp;&nbsp;위치정보 이용약관,(선택), 프로모션 정보 수신(선택)에 모두 동의합니다.</strong></label>
	</div><br>
	<div>
		<label><input type="checkbox" name="agreeTerms" value="serviceAgree"><strong>서비스 이용약관 동의</strong></label>(필수)<br>
		<textarea rows="5" cols="30">
			<b>@@@ 페이지에 오신것을 환영합니다.</b><br>
			@@@ 서비스 및 제품(이하 '서비스')을 이용해주셔서 감사합니다. 본 약관은
			다양한 @@@ 서비스의 이용과 관련하여 @@@ 서비스를 제공하는 @@@ 주식회사(이하 '@@@')와 이를 이용하는 @@@ 서비스 회원(이하 '회원')
			또는 비회원과의 관계를 설명하며, 아울러 여러분의 네이버 서비스 이용에 도움이 될 수 있는 유익한 정보를 포함하고 있습니다.
			@@@ 서비스를 이용하시거나 네이버 서비스 회원으로 가입하실 경우 여러분은 본 약관 및 관련 운영 정책을 확인하거나 동의하게 되므로, 
			잠시 시간을 내시어 주의 깊게 살펴봐 주시기 바랍니다.<br>
			<b>다양한 @@@ 서비스를 즐겨보세요.</b>
		</textarea><br>
	</div><br>
	<div>
		<label><input type="checkbox" name="agreeTerms" value="personalInformationAgree"><strong>정보 수집 및 이용 동의</strong></label>(필수)<br>
		<textarea rows="5" cols="30">
			개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 
			개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.
		</textarea><br>
	</div><br>
	<div>
		<label><input type="checkbox" name="agreeTermsSel" value="locationInformationAgree"><strong>위치정보 이용약관 동의</strong></label>(선택)<br>
		<textarea rows="5" cols="30">
			위치정보 이용약관에 동의하시면, 위치를 활용한 광고 정보 수신 등을 포함하는 @@@ 위치기반 서비스를 이용할 수 있습니다.
		</textarea><br>
	</div><br>
	<div>
		<label><input type="checkbox" name="agreeTermsSel" value="promotionInformationAgree"><strong>위치정보 이용약관 동의</strong></label>(선택)<br>
		<textarea rows="5" cols="30">
			@@@에서 제공하는 이벤트/혜택 등 다양한 정보를 휴대전화, 이메일로 받아보실 수 있습니다.<br>
			일부 서비스(별도 회원 체계로 운영하거나 네이버 가입 이후 추가 가입하여 이용하는 서비스 등)의 경우,
			개별 서비스에 대해 별도 수신 동의를 받을 수 있으며, 이때에도 수신 동의에 대해 별도로 안내하고 동의를 받습니다.
		</textarea><br>
	</div><br>
	<input type="button" onclick="javascript:'history.go(-1)''" value="취소"/>
	<input type="submit" value="확인">
	</form:form>
</body>
</html>