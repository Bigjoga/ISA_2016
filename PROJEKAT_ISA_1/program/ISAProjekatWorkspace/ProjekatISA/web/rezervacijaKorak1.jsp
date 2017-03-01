<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Rezervacija - Korak 1</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		
		<SCRIPT type="text/javascript">
			function searchSel() {
			  var input=document.getElementById('realtxt').value.toLowerCase();
			  var output=document.getElementById('realitems').options;
			  for(var i=0;i<output.length;i++) {
			    if(output[i].text.toLowerCase().indexOf(input)>-1){
			      output[i].selected=true;
			    }
			    if(document.getElementById('realtxt').value==''){
			      output[0].selected=true;
			    }
			  }
			}
		</SCRIPT>
		
	</head>
	<c:if test="${sessionScope.posetilac==null}">
		<c:redirect url="./login.jsp" />
	</c:if>
	<body>
		Posetilac: ${sessionScope.posetilac.ime}
		[<a href="./LogoutController">odjava</a>]<br/>
		<div class="menu_simple">
		<ul>
			<li><a href="./PosetePosetilacController">Moje posete</a></li>
			<li><a href="./RestoraniPosetilacController">Restorani</a></li>
			<li><a href="./PrijateljiReadController">Prijatelji</a></li>
			<li><a href="./PosetilacReadController">Moj nalog</a></li>
			<br />
		</ul>
		<br />
		</div>
		<br>
		<br>
		
		
		<form align="left" class="form-basic" method="post" action="./RezervacijaKorak2Controller?id=${res.id}">
			<div class="form-title-row">
                <h1>Izbor datuma i vremena</h1>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Restoran</span>
                    <input type="text" name="restoran" value="${res.nazivRestorana}" disabled>
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Dan</span>
                    <input type="number" name="dan">
                </label>
            </div>

			<div class="form-row">
                <label>
                    <span>Mesec</span>
                    <input type="number" name="mesec">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Godina</span>
                    <input type="number" name="godina">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Sat</span>
                    <input type="number" name="sat">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Minut</span>
                    <input type="number" name="minut">
                </label>
            </div>
            
            <div class="form-row">
                <label>
                    <span>Trajanje (u satima)</span>
                    <input type="number" name="trajanje">
                </label>
            </div>
	

			<div class="form-row">
                <button type="submit">Dalje>></button>
            </div>
		</form>
				
	</body>	
</html>