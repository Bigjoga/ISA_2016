<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Dodaj menadzera restorana</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
	</head>
	<c:if test="${sessionScope.admin==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		Administrator sistema: ${sessionScope.admin.ime}
		[<a href="./LogoutController">odjava</a>]<br/>
		<br>
		<br>
		[<a href="./PocetnaAdminController">lista restorana</a>]<br/>
		<br>
		<br>
		[<a href="./RestoranUpdateController?id=${restoran.id}">vrati se na izmenu restorana</a>]<br/>
		<br>
		<br>
		<form class="form-basic" method="post" action="./MenadzerCreateController?resId=${restoran.id}">
			<div class="form-title-row">
                <h1>Dodavanje menadzera restorana</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Naziv restorana</span>
                    <input type="text" name="naziv" value="${restoran.nazivRestorana}" disabled>
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Opis restorana</span>
                    <input type="text" name="opis" value="${restoran.opisRestorana}" disabled>
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>Korisnici</span>
                    <select name="korId">
                        <c:forEach items="${posetioci}" var="pos">
							<option value="${pos.id}">${pos.ime}</option>
						</c:forEach>
                    </select>
                </label>
            </div>
			
			
			<div class="form-row">
                <button type="submit">Dodaj menadzera</button>
            </div>
		</form>
		
	</body>	
</html>