<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Izmena jelovnika</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./table.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
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
		[<a href="./JelovniciMenadzerController">lista jelovnika restorana</a>]<br/>
		<br>
		<br>
		<form class="form-basic" method="post" action="./JelovnikUpdateController?id=${jelovnik.id}">
			<div class="form-title-row">
                <h1>Izmena jelovnika</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Naziv jelovnika</span>
                    <input type="text" name="naziv" value="${jelovnik.nazivJelovnika}">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                	<a href="./PreCrStavkuZaJelovnikController?id=${jelovnik.id}">Dodaj novu stavku</a>
                </label>
            </div>
		
			<div class="CSSTableGenerator" >
				<table class="listaStavkiJelovnika">
					<tbody>
						<tr>
							<td>Naziv jela</td>
							<td>Opis jela</td>
							<td>Cena</td>
							<td>&nbsp;</td>
						</tr>
						<c:forEach items="${stavkeJelovnika}" var="stavka">
							<tr>
								<td>${stavka.jelo.nazivJela}</td>
								<td>${stavka.jelo.opisJela}</td>
								<td><input type="text" name="cena${stavka.id}" value="${stavka.cena}"></td>
								<td><a href="./DeleteStavkuIzJelovnikaController?id=${stavka.id}">ukloni iz jelovnika</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			
			<div class="form-row">
                <button type="submit">Izmeni jelovnik</button>
            </div>
		</form>
		
	</body>	
</html>