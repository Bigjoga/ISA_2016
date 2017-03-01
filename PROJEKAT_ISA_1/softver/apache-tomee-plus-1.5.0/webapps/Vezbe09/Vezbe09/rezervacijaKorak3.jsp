<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Rezervacija - Korak 3</title>
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
			
			function funPozovi() {
				var prijaComboBox = document.getElementById('realitems');
				var prijaId = prijaComboBox.value;
				var prijaCheckBox = document.getElementById("cb" + prijaId);
				prijaCheckBox.checked = true;
			}
			
			function funDalje() {
			  var checkedPrijatelji = document.getElementsByClassName('cbPrija');
			  stringId="";
			  for(var i=0; i<checkedPrijatelji.length; i++) {
			    if (checkedPrijatelji[i].checked) {
			  		stringId += checkedPrijatelji[i].id + "_";
			  	}
			  }
			  document.location.href="./RezervacijaCreateController?id=" + stringId;
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
		<td align="center" colspan="2">
		
			Search <input type="text" id="realtxt" onkeyup="searchSel()" placeholder="prijatelji...">
			<SELECT id="realitems" name="korId">
				<c:forEach items="${prijatelji}" var="prija">
					<option value="${prija.id}">${prija.ime}</option>
				</c:forEach>
			</SELECT>
			<button onclick="funPozovi()">Pozovi prijatelja</button>
		
		
		</td>
		</tr>
		<tr>
		<td>
		
		
			<div >
				<table border="7" class="sortable" >
					<caption>PRIJATELJI </caption>
					
					<thead>
						<tr>
							<th>Ime i prezime</th>
							<th>Pozvan</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${prijatelji}" var="prija" varStatus="loop">
							<tr>
								<td>${prija.ime}</td>
								<td><input type="checkbox" class="cbPrija" id="cb${prija.id}"></input></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		
		
		</td>
		<td align="center" valign="top">
		
		
			<button onclick="funDalje()">Potvrdi>></button>
		
		
		</td>
		</tr>
		</tbody>
		</table>
		
		
						
	</body>	
</html>