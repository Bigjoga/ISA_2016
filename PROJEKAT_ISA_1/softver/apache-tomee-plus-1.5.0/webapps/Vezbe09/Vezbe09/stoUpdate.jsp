<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Izmena stola</title>
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
		[<a href="./StoloviMenadzerContoller">lista stolova restorana</a>]<br/>
		<br>
		<br>
		<form class="form-basic" method="post" action="./StoUpdateController?id=${id}">

            <div class="form-title-row">
                <h1>Izmena stola</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Naziv stola</span>
                    <input type="text" name="naziv" value="${naziv}">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Broj mesta</span>
                    <input type="text" name="brojMesta" value="${brojMesta}">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>X pozicija stola</span>
                    <input type="text" name="pozX" value="${pozX}">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>Y pozicija stola</span>
                    <input type="text" name="pozY" value="${pozY}">
                </label>
            </div>

            <div class="form-row">
                <button type="submit">Izmeni sto</button>
            </div>

        </form>
		
	</body>	
</html>