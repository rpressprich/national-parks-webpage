<%@include file="common/header.jspf" %>

<table>
<c:set var="i" value ="0"/> <!-- int i=0 -->
<c:forEach var="park" items="${parks}" >
<tr>
<!-- Creates a link from the park image to go to the park detail page -->
<td><a href="<c:url value="/parkInfo?parkCode=${park.parkCode}"/>"><img src="img/parks/${parkCodes[i]}.jpg" alt="img/parks/${parkCodes[i]}.jpg"/></a></td>
<td><p id="name"><b>${park.parkName}</b>, ${park.state}</p>
<p>${park.description}</p>
</td>

</tr>
<c:set var="i" value="${i + 1}"/> <!-- i++ iterating i to retrieve value of each image-->
</c:forEach>
</table>

</body>
</html>