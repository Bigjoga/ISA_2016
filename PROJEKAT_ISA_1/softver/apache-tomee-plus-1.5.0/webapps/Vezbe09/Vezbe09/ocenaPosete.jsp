<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Ocena posete</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		<link href="./starrating.css" rel="stylesheet" type="text/css"  />
		
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
				<td align="right">Restoran:</td><td>${naziv}</td>
			</tr>
			<tr>
				<td align="right">Datum i vreme:</td><td>${datumStr}</td>
			</tr>
			<tr>
				<td align="right">Trajanje (u satima):</td><td>${trajanje}</td>
			</tr>
			<tr>
				<td align="right">Rezervaciju kreirao:</td><td>${autor}</td>
			</tr>
			</tbody>
			</table>			
		
		
		</td>
		</tr>
		<tr>
		<td>
		
		
			<div >
				<table border="7" class="sortable" >
					<caption>Ostali gosti </caption>
					
					<thead>
						<tr>
							<th>Ime i prezime</th>
						</tr>
					</thead>
					
					<tbody>
						<c:forEach items="${gosti}" var="gos" varStatus="loop">
							<tr>
								<td>${gos.ime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		
		
		</td>
		<td align="center" valign="top">
		
			<form align="center" class="form-basic" method="post" action="./OcenaPoseteController?id=${poziv.id}">
				
				<div class="form-title-row">
	                <h1>Oceni posetu</h1>
	            </div>
		
				<div class="form-row">
	                <label>
	                    <span>Ocena</span>
	                    <input type="number" name="ocena" min="1" max="5" step="0.1">
	                </label>
	            </div>
	            
	            <div class="form-row">
	                <button type="submit">Potvrdi</button>
	            </div>
			
			</form>
		
		
		</td>
		</tr>
		</tbody>
		</table>
		
		
						
	</body>	
</html>