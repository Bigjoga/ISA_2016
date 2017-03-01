<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:setBundle basename="messages.messages"/>

<html>
	<head>
		<title>Registracija</title>
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		
		<SCRIPT type="text/javascript">
			function sifre() {
			  var loz=document.getElementById('loz').value;
			  var lozProvera=document.getElementById('lozProvera').value;
			  
			  if (loz != lozProvera) {
			  	alert("Sifra nije ponovljena kako treba.");
			  	window.location.href = "./registracija.jsp";
			  	return false;
			  }
			  
			}
		</SCRIPT>
		
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
	
		<form class="form-basic" method="post" action="./PosetilacCreateController" onsubmit="return sifre()">
			<div class="form-title-row">
                <h1>Registracija</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>*Email</span>
                    <input type="text" name="email">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>*Sifra</span>
                    <input type="password" name="lozinka" id="loz">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>*Ponoviti sifru</span>
                    <input type="password" name="lozinkaProvera" id="lozProvera">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Ime</span>
                    <input type="text" name="ime">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Adresa</span>
                    <input type="text" name="adresa">
                </label>
            </div>

			<div class="form-row">
                <button type="submit">Registruj se</button>
            </div>
		</form>
		
		<br><br>
	
		<a href="./login.jsp"><button>Povratak na prijavljivanje</button></a>
		
	
	</td>
	</tr>
	</table>	
		
	<body>	
</html>