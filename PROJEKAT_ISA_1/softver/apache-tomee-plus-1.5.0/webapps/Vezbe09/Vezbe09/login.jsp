<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.messages"/>

<html>
	<head>
		<title><fmt:message key="prijava"/></title>
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
	</head>
		<c:if test="${sessionScope.posetilac!=null}">
			<c:redirect url="./PosetilacReadController" />
		</c:if>
		
		<c:if test="${sessionScope.menadzer!=null}">
			<c:redirect url="./JelovniciMenadzerController" />
		</c:if>
		
		<c:if test="${sessionScope.admin!=null}">
			<c:redirect url="./PocetnaAdminController" />
		</c:if>
	<body>
	
		
	<table align="center">
	<tr>
	<td align="center">
	
		<form class="form-basic" method="post" action="./LoginController?pozId=${id}">
			<div class="form-title-row">
                <h1>Prijavi se</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Email</span>
                    <input type="text" name="email">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Sifra</span>
                    <input type="text" name="lozinka">
                </label>
            </div>

			<div class="form-row">
                <button type="submit">Prijavi se</button>
            </div>
		</form>
		
		<br><br>
	
		<a href="./registracija.jsp"><button>Registracija novog korisnika</button></a>
		
		<br>
		<br>
		<c:if test="${upoz == 1}">
            <font color="red"><i>Vec postoji korisnik sa unetom email adresom.</i></font>
        </c:if>
	
	</td>
	</tr>
	</table>	
		
	<body>	
</html>