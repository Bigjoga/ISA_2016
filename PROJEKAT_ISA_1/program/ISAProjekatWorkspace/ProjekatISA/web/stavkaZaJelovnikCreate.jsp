<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Dodavanje nove stavke jelovnika</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
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
		<form class="form-basic" method="post" action="./StavkaJelovnikaCreateController?jelovnikId=${jelovnikId}">

            <div class="form-title-row">
                <h1>Kreiranje stavke jelovnika</h1>
            </div>

			<div class="form-row">
                <label>
                    <span>Jelo</span>
                    <select name="jeloId">
                        <c:forEach items="${jela}" var="jelo">
							<option value="${jelo.id}">${jelo.nazivJela}</option>
						</c:forEach>
                    </select>
                </label>
            </div>

            <div class="form-row">
                <label>
                    <span>Cena</span>
                    <input type="text" name="cena">
                </label>
            </div>

            <div class="form-row">
                <button type="submit">Kreiraj stavku jelovnika</button>
            </div>

        </form>
		
	</body>	
</html>