<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file = "../header.html" %>
<link rel = "stylesheet" href = "../member.css">
<p align="center">メニュー画面</p>
<form action = "../jp.co.aforce.member/" method = "post">
<table align="center">
	<tr>

		<td><input type = "submit" value = "会員情報新規登録" formaction = "insert.jsp"></td>
	</tr>
	<tr>
		<td><input type = "submit" value = "会員情報変更" formaction = "update.jsp"></td>
	</tr>
	<tr>
		<td><input type = "submit" value = "会員情報削除" formaction = "delete.jsp"></td>
	</tr>
</table>
</form>
<%@include file = "../footer.html" %>