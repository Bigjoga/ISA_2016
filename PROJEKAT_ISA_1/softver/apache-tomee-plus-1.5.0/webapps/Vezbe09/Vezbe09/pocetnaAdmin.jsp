<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="restorani" type="java.util.List" scope="request"/>

<html>
	<head>
		<title>Pregled svih restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		
		<link href="./tablesort.css" rel="stylesheet" type="text/css" />
		
		<script src="sorttable.js"></script>
		
	</head>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		Administrator sistema: ${sessionScope.admin.ime}
		[<a href="./LogoutController">odjava</a>]<br/>
		<div class="menu_simple">
		<ul>
			<li><a href="./PocetnaAdminController">Restorani</a></li>
			<li><a href="./JelaAdminController">Jela</a></li>
			<br />
		</ul>
		<br />
		</div>
		<br>
		<br>
		<a href="./restoranCreate.jsp"><button>Dodaj novi restoran</button></a>
		<br>
		<br>
		<table style="width:100%">
		<tbody>
		<tr>
		<td align="center">
		
		<br>
		<br>
		<br>
		
		
		<div >
			<table border="7" class="sortable" >
				<caption>RESTORANI </caption>
				
				<thead>
					<tr>
						<th>Naziv</th>
						<th>Opis</th>
						<th>Adresa</th>
						<th>Broj stolova po sirini</th>
						<th>Broj stolova po visini</th>
						<th>Menadzer(i)</th>
						<th>Prosecna ocena korisnika</th>
						<th>Akcije</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${restorani}" var="restoran" varStatus="loop">
						<tr>
							<td>${restoran.nazivRestorana}</td>
							<td>${restoran.opisRestorana}</td>
							<td>${restoran.adresa}</td>
							<td>${restoran.dimX}</td>
							<td>${restoran.dimY}</td>
							<td>
								
										<c:forEach items="${restoran.menadzeri}" var="men">
											${men.ime}<br>
										</c:forEach>
									
							</td>
							<td>${ocene[loop.index]}</td>
							<td>
								<a href="./PreUpRestoranController?id=${restoran.id}">izmeni</a><br>
								<a href="./RestoranDeleteController?id=${restoran.id}">obrisi</a>
							</td>
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