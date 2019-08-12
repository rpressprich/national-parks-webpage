<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@include file="common/header.jspf" %>

<c:set var="url" value="<c:url value="/survey"/>"/>
<form:form method="POST" action="${url}" modelAttribute="survey">
	<div>
		<form:label path="parkCode" for="parkCode">Park Name</form:label>
		<form:select path="parkCode">
			<c:forEach var="park" items="${parks}">
				<form:option value="${park.parkCode}">${park.parkName}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="parkCode" cssClass="error"/>
	</div>

	<div>
		<form:label path="email" for="email">Email</form:label>
		<form:input path="email"/>
		<form:errors path="email" cssClass="error"/>
	</div>

	<div>
		<form:label path="state" for="state">State</form:label>
		<form:select path="state">
			<c:forEach var="state" items="${states}">
				<form:option value="${state}">${state}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="state" cssClass="error"/>
	</div>

	<div>
		<form:label path="activityLevel" for="activityLevel">State</form:label>
		<form:select path="activityLevel">
			<c:forEach var="activityLevel" items="${activityLevels}">
				<form:option value="${activityLevel}">${activityLevel}</form:option>
			</c:forEach>
		</form:select>
		<form:errors path="activityLevel" cssClass="error"/>
	</div>
	
	<form:button type="submit">Submit</form:button>
</form:form>
</body>
</html>