<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Pregled svih jela</title>
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
		<a href="./jeloCreate.jsp"><button>Dodaj novo jelo</button></a>
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
				<caption>JELA </caption>
				
				<thead>
					<tr>
						<th>Naziv</th>
						<th>Opis</th>
						<th>Akcije</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${jela}" var="j" varStatus="loop">
						<tr>
							<td>${j.nazivJela}</td>
							<td>${j.opisJela}</td>
							<td>
								<a href="./PreUpJeloController?id=${j.id}">izmeni</a><br>
								<a href="./JeloDeleteController?id=${j.id}">obrisi</a>
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