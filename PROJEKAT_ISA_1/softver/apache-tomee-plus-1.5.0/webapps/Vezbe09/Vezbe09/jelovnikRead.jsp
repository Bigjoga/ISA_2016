<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="stavkeJelovnika" type="java.util.List" scope="request"/>

<html>
	<head>
		<title>Jelovnik restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		
		<link href="./tablesort.css" rel="stylesheet" type="text/css" />
		
		<script src="sorttable.js"></script>

	</head>
	<c:if test="${sessionScope.menadzer==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		Menadzer restorana: ${sessionScope.menadzer.ime}
		[<a href="./LogoutController">odjava</a>]<br/>
		<div class="menu_simple">
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
		
		[<a href="./JelovniciMenadzerController">lista jelovnika restorana</a>]<br/>
		<br>
		<br>
		
		<table style="width:100%">
		<tbody>
		<tr>
		<td align="center">
		
		<div>
			<table border="7" class="sortable">
				<caption>JELOVNIK: ${jelovnik.nazivJelovnika}</caption>
				
				<thead>
					<tr>
						<th>Naziv jela</th>
						<th>Opis jela</th>
						<th>Cena</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${stavkeJelovnika}" var="stavka">
						<tr>
							<td>${stavka.jelo.nazivJela}</td>
							<td>${stavka.jelo.opisJela}</td>
							<td>${stavka.cena}</td>
						</tr>
					</c:forEach>
				</tbody>
				
			</table>
		</div>
		
		
		</td>
		
		</tr>
		</tbody>
		</table>
		
	</body>	
</html>