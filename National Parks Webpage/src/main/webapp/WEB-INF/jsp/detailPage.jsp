<%@include file="common/header.jspf" %>

<table>
<tr>
<td><img class="parkImg" src="img/parks/${parkCode}.jpg" alt="${park.parkName}"/></td>
<td><p id="name"><b>${park.parkName}</b> (${park.state})</p>
<p>${park.description}</p>
<div><b>Acreage (acres):</b>   ${park.acreage}</div>
<div><b>Elevation (ft.):</b>   ${park.elevationFt}</div>
<div><b>Trails (miles):</b>    ${park.miles}</div>
<div><b>Campsites:</b> ${park.noOfCampsites}</div>
<div><b>Climate:</b> ${park.climate}</div>
<div><b>Annual Visitors:</b> ${park.annualVisitors}</div>
<div><b>Year Founded:</b> ${park.yearFounded}</div>
<div><b>Quote:</b> ${park.quote} - ${park.quoteSource}</div>
<div><b>Entry Fee:</b> $ ${park.entryFee}</div>
<div><b>Animal Species:</b> ${park.animalSpecies}</div>
</td>
</tr>
</table>

<h1>5 Day Weather Report</h1>
<table>
<c:set var="i" value="0"/> <!-- int i = 0 -->
<c:forEach var="dayReport" items="${weatherReport}">
<tr>
<td>${dayNames[i]}</td>
<c:choose>
	<c:when test="${temperature == 'F'}">
		<td>Low: ${dayReport.low} F°</td>
		<td>High: ${dayReport.high} F°</td>
	</c:when>
	<c:otherwise>
		<td>Low: ${(dayReport.low - 32) * 5 / 9 - ((dayReport.low - 32) * 5 / 9) % 1} C°</td>
		<td>High: ${(dayReport.high - 32) * 5 / 9 - ((dayReport.high - 32) * 5 / 9) % 1} C°</td>
	</c:otherwise>
</c:choose>
<td class=weather><img src="img/weather/${dayReport.forecast}.png"/></td>
<td>${forecastMessages[i]}</td>
</tr>
<c:set var="i" value="${i + 1}"/> <!-- i++ -->
</c:forEach>
</table>

<br>
<form action="<c:url value="/changeTemperature"/>" method="POST">
<button type="submit" name="parkCode" value="${park.parkCode}">Change to ${unusedTemperature}°.</button>
</form>
</body>
</html>