<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<html>
	<head>
		<title>Restorani u ponudi</title>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta HTTP-EQUIV="Pragma" CONTENT="no-cache">
		<meta HTTP-EQUIV="Expires" CONTENT="-1">
		<link href="./simplemenu.css" rel="stylesheet" type="text/css" />
		<link href="./form-basic.css" rel="stylesheet" type="text/css" />
		
		<link href="./tablesort.css" rel="stylesheet" type="text/css" />
		
		<script src="sorttable.js"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js"></script>
		
		<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
			   
			    var restaurantsSize = parseInt($("#restaurantsSize").val());
			    var userAddress = $("#userAddress").val()
			   
			    currentIndex = 0;
			   
			    for(var i = 0; i < restaurantsSize; i++){
			        var restaurantAddress = $("#distance" + i)[0].textContent;
			       
			        var service = new google.maps.DistanceMatrixService();
			        service.getDistanceMatrix(
			          {
			            origins: [userAddress],
			            destinations: [restaurantAddress],
			            travelMode: google.maps.TravelMode.DRIVING,
			          }, callback);
			 
			        function callback(response, status) {
			            if (status == google.maps.DistanceMatrixStatus.OK) {
			                var origins = response.originAddresses;
			                var destinations = response.destinationAddresses;
			               
			                for (var i = 0; i < origins.length; i++) {
			                  var results = response.rows[i].elements;
			                  for (var j = 0; j < results.length; j++) {
			                    var element = results[j];
			                    var distance = element.distance.text;
			                    var distanceNumber = parseFloat(distance.replace(',','.').replace(' ',''))
			                    $("#distance" + currentIndex).html(' ' + distanceNumber);
			                    currentIndex++;
			                  }
			                }
			             }
			        }
			    }
			});
			 
		</script>
		
		
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
		
		
		<input type="hidden" id="restaurantsSize" value="${fn:length(restorani)}">
        <input type="hidden" id="userAddress" value="${kor.adresa}">
		
		
		<table style="width:100%">
		<tbody>
		<tr>
		<td align="center">
		
		<br>
		<br>
		<br>
		
		<div >
			<table border="7" class="sortable" >
				<caption>RESTORANI </caption>
				
				<thead>
					<tr>
						<th>Ime restorana</th>
						<th>Opis</th>
						<th>Adresa</th>
						<th>Udaljenost[km]</th>
						<th>Rejting</th>
						<th>Ocena prijatelja</th>
						<th>Rezervacija</th>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${restorani}" var="res" varStatus="loop">
						<tr>
							<td>${res.nazivRestorana}</td>
							<td>${res.opisRestorana}</td>
							<td><c:out value="${res.adresa}" /></td>
                        	<td id="distance${loop.index}"><c:out value="${res.adresa}" /></td>
							<td>${ocene[loop.index]}</td>
							<td>${prOcene[loop.index]}</td>
							<td><a href="./RezervacijaKorak1Controller?id=${res.id}"><button>Rezervisi</button></a></td>
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