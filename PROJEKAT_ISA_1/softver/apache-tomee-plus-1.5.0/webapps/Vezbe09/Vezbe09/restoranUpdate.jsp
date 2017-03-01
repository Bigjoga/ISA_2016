<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Izmena restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		
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
		<form class="form-basic" method="post" action="./RestoranUpdateController?id=${restoran.id}">
			<div class="form-title-row">
                <h1>Izmena restorana</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Naziv restorana</span>
                    <input type="text" name="naziv" value="${restoran.nazivRestorana}">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Opis restorana</span>
                    <input type="text" name="opis" value="${restoran.opisRestorana}">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Adresa restorana</span>
                    <input type="text" name="adresa" value="${restoran.adresa}">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>Broj stolova po x-osi</span>
                    <input type="number" name="dimX" value="${restoran.dimX}">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>Broj stolova po y-osi</span>
                    <input type="number" name="dimY" value="${restoran.dimY}">
                </label>
            </div>
            
            
            
            <div class="form-row">
                <label>
                	<a href="./PreCrMenadzerRestoranaController?id=${restoran.id}">Dodaj novog menadzera</a>
                </label>
            </div>
		
		
			<div class="form-row">
                <label>
                	<table border="3" align="center" class="sortable">
                		<caption>lista menadzera</caption>
                		
                		<thead>
						    <tr>
						      <th>ime i prezime</th>
						      <th>akcija</th>
						    </tr>
					  	</thead>
                		
                		<tbody>
                			<c:forEach items="${menadzeri}" var="men">
								<tr>
									<td>${men.ime}</td>
									<td><a href="./DeleteMenadzeraIzRestoranaController?id=${men.id}">ukloni iz liste</a></td>
								</tr>
							</c:forEach>
                		</tbody>
                	</table>
                </label>
            </div>
			
			
			<div class="form-row">
                <button type="submit">Izmeni restoran</button>
            </div>
		</form>
		
	</body>	
</html>