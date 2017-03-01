<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Stolovi restorana</title>
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
		
		
		<table cellpadding="15" border="3">
			<caption>RASPORED STOLOVA</caption>
			<tbody>
				<c:forEach var="j" begin="0" end="${visina-1}">
            		<tr>
            			<c:forEach var="i" begin="0" end="${sirina-1}">
            				<td>
            				<c:set var="postoji" value="0"/> 
            				<c:forEach items="${stolovi}" var="sto">
            					<c:if test="${sto.pozX == i && sto.pozY == j}">
            						<c:set var="postoji" value="1"/>
            						${sto.nazivStola} [${sto.brojMesta}]<br>
            						<a href="./PreUpStoController?id=${sto.id}">izmeni sto</a>
            						<a href="./StoDeleteController?id=${sto.id}">ukloni sto</a>
            					</c:if>
            				</c:forEach>
            				<c:if test="${postoji == 0}">
            					<a href="./PreCrStoController?pozX=${i}&pozY=${j}"><button>Dodaj sto</button></a>
            				</c:if>
            				</td>
            			</c:forEach>
            		</tr>
		        </c:forEach>
			</tbody>
		</table>
		
		
	</body>	
</html>