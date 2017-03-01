<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Prijatelji</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		<link href="./tablesort.css" rel="stylesheet" type="text/css" />
		
		<script src="sorttable.js"></script>
		
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
		
		<table style="width:100%">
		<tbody>
		<tr>
		<td align="center">
		
		<br>
		<br>
		<br>
		
		<div >
			<table border="7" class="sortable" >
				<caption>PRIJATELJI </caption>
				
				<thead>
					<tr>
						<th>Ime i prezime</th>
						<th>Broj poseta</th>
						<th>Akcija</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${prijatelji}" var="prija" varStatus="loop">
						<tr>
							<td>${prija.ime}</td>
							<td>${listaPoseta[loop.index]}</td>
							<td><a href="./PrijateljiDeleteController?adr=pr&korId=${prija.id}">ukloni</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		</td>
		
		<td align="center">
		
		<form align="left"\ method="post" action="./PrijateljCreateController?adr=pr">
			Search <input type="text" id="realtxt" onkeyup="searchSel()" placeholder="prijatelji...">
			<SELECT id="realitems" name="korId">
				<c:forEach items="${korisnici}" var="kor">
					<option value="${kor.id}">${kor.ime}</option>
				</c:forEach>
			</SELECT>
			<button type="submit">Dodaj prijatelja</button>
		</form>
		
		<br>
		<c:if test="${upoz == 1}">
            <font color="red"><i>Vec ste prijatelji. Pokusajte da dodate drugog korisnika za prijatelja.</i></font>
        </c:if>
		
		
		</td>
		
		</tr>
		</tbody>
		</table>
		
	</body>	
</html>