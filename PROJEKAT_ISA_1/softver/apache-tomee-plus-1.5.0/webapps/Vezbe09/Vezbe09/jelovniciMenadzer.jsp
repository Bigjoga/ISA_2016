<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Jelovnici restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
	</head>
	<c:if test="${sessionScope.menadzer==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		Menadzer restorana: ${sessionScope.menadzer.ime}
		[<a href="./LogoutController">odjava</a>]<br/>
		<div class="menu_simple" style="width:95%;">
		<ul>
			<li><a href="./JelovniciMenadzerController">Jelovnici</a></li>
			<li><a href="./StoloviMenadzerContoller">Konfiguracija stolova</a></li>
			<br />
		</ul>
		<br />
		</div>
		<br>
		<br>
		
		<table>
		<tbody cellpadding="10">
		<tr>
		<td align="right" >Naziv restorana:</td><td>${sessionScope.restoran.nazivRestorana}</td>
		</tr>
		<tr>
		<td align="right" >Opis restorana:</td><td>${sessionScope.restoran.opisRestorana}</td>
		</tr>
		<tr>
		<td align="right" >Adresa restorana:</td><td>${sessionScope.restoran.adresa}</td>
		</tr>
		</tbody>
		</table>
		
		<br>
		<br>
		<br>
		<a href="./jelovnikCreate.jsp">dodaj novi jelovnik</a>
		<br>
		<br>
		<ul>
			Ponudjeni jelovnici u restoranu:<br>
			<c:forEach items="${jelovnici}" var="jelovnik">
				<li>[<a href="./JelovnikReadController?id=${jelovnik.id}">${jelovnik.nazivJelovnika}]</a> 
				<a href="./PreUpJelovnikController?id=${jelovnik.id}">izmeni</a>
				<a href="./JelovnikDeleteController?id=${jelovnik.id}">obrisi</a>
				</li>
			</c:forEach>
		</ul>
		
	</body>	
</html>