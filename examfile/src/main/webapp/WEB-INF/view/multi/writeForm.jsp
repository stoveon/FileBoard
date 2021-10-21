<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다중파일업로드</title>
<script>
        const add_box = () => {
            const box = document.getElementById("box");
            const newP = document.createElement('p');
            newP.innerHTML = "<input type='file' name='imageFile'> <input type='button' value='삭제' onclick='remove(this)'>";
            box.appendChild(newP);
        }
        const remove = (obj) => {
            document.getElementById('box').removeChild(obj.parentNode);
        }
</script>

</head>
<body>
	<form action="<c:url value="/multi/multiUpload" />" method="post" enctype=multipart/form-data>
		<table>
		<tr>
			<td>제목 : </td>
			<td><input type="text" name="title" /></td>
		</tr>
		<tr>
			<td>파일 : </td>
			<td>
				<div id="box">
					<input type="file" name="imageFile" /><input type="button" value="추가" onclick="add_box()"/>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="2"><textarea cols="30" rows="6" placeholder="내용을 입력하세요." name="content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="저장"></td>
		</tr>
		</table>
	</form>
</body>
</html>