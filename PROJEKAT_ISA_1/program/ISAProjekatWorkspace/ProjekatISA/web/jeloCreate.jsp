<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<title>Dodavanje novog jela</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
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
		<form class="form-basic" method="post" action="./JeloCreateController">

            <div class="form-title-row">
                <h1>Kreiranje jela</h1>
            </div>

            <div class="form-row">
                <label>
                    <span>Naziv jela</span>
                    <input type="text" name="naziv">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Opis jela</span>
                    <input type="text" name="opis">
                </label>
            </div>

            <div class="form-row">
                <button type="submit">Kreiraj jelo</button>
            </div>

        </form>
		
	</body>	
</html>