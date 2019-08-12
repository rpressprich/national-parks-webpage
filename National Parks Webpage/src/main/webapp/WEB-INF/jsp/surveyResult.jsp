<%@include file="common/header.jspf" %>

<p><b>${park.parkName}</b> is the most popular park!</p>
<br>
<c:forEach var="results" items="${surveysPerPark}">
	<p>${results.parkCode} has ${results.surveys} votes</p>
</c:forEach>
</body>
</html>