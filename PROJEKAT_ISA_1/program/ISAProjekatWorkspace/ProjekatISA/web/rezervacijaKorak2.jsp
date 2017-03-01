<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Rezervacija - Korak 2</title>
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
			
			function funDalje() {
			  var checkedStolovi = document.getElementsByClassName('cbSto');
			  stringId="";
			  for(var i=0; i<checkedStolovi.length; i++) {
			    if (checkedStolovi[i].checked) {
			  		stringId += checkedStolovi[i].id + "_";
			  	}
			  }
			  document.location.href="./RezervacijaKorak3Controller?id=" + stringId;
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
		
		<table cellpadding="30">
		<tbody>
		<tr>
		<td align="left" colspan="2">
		
		
			<table>
			<tbody cellpadding="10">
			<tr>
				<td align="right">Restoran:</td><td>${sessionScope.res.nazivRestorana}</td>
			</tr>
			<tr>
				<td align="right">Datum i vreme:</td><td>${sessionScope.datumStr}</td>
			</tr>
			<tr>
				<td align="right">Trajanje (u satima):</td><td>${sessionScope.trajanje}</td>
			</tr>
			</tbody>
			</table>			
		
		
		</td>
		</tr>
		<tr>
		<td align="center">
		
		
			<table cellpadding="15" border="3">
				<caption>REZERVISI STO</caption>
				<tbody>
					<c:forEach var="j" begin="0" end="${sessionScope.res.dimY-1}">
	            		<tr>
	            			<c:forEach var="i" begin="0" end="${sessionScope.res.dimX-1}">
	            				<td>
	            				<c:set var="postoji" value="0"/> 
	            				<c:forEach items="${stolovi}" var="sto" varStatus="loop">
	            					<c:if test="${sto.pozX == i && sto.pozY == j}">
	            						<c:set var="postoji" value="1"/>
	            						
	            						<c:if test="${not zauzeti[loop.index]}">
	            							<input type="checkbox" class="cbSto" id="${sto.id}">${sto.nazivStola} [${sto.brojMesta}]</input>
	            						</c:if>
	            						<c:if test="${zauzeti[loop.index]}">
	            							<input type="checkbox" disabled><strike>${sto.nazivStola} [${sto.brojMesta}]</strike></input>
	            						</c:if>
	            						
	            					</c:if>
	            				</c:forEach>
	            				<c:if test="${postoji == 0}">
	            					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	            				</c:if>
	            				</td>
	            			</c:forEach>
	            		</tr>
			        </c:forEach>
				</tbody>
			</table>
		
		
		</td>
		<td align="center" valign="bottom">
		
		
			<button onclick="funDalje()">Dalje>></button>
		
		
		</td>
		</tr>
		</tbody>
		</table>
		
		
						
	</body>	
</html>