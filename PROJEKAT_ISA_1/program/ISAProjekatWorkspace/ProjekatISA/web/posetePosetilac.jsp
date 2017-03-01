<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
	<head>
		<title>Moje posete</title>
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
				<caption>MOJE POSETE </caption>
				
				<thead>
					<tr>
						<th>Restoran</th>
						<th>Datum</th>
						<th>Trajanje [h]</th>
						<th>Ocena</th>
						<th>Ostali gosti</th>
						<th>*****</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${restorani}" var="res" varStatus="loop">
						<tr>
							<td>${res}</td>
							<td>${datumi[loop.index]}</td>
							<td>${trajanje[loop.index]}</td>
							<td>${ocene[loop.index]}</td>
							<td>${gosti[loop.index]}</td>
							<td>
								<c:if test="${dozvoljeneOcene[loop.index]}">
									<a href="./PreOcenaPoseteController?id=${poseteIds[loop.index]}"><button>Oceni</button></a>
	            				</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		</td>
		
		<td align="center">
		
		</td>
		
		</tr>
		</tbody>
		</table>
		
	</body>	
</html>