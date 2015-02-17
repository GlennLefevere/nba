<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:url value="/" var="urlNext">
		<c:param name="day" value="${day - 1}"/>
	</c:url>
	<c:url value="/" var="urlPrev">
		<c:param name="day" value="${day + 1}"/>
	</c:url>
	<a href="${urlNext}">&lt;Previous</a>
	<a href="${urlPrev}">Next&gt;</a>
	<h1>${datum}</h1>
	<c:forEach items="${games}" var="game">
		<h2>${game.visitor.nickname} ${game.visitorScore} - ${game.homeScore} ${game.home.nickname}</h2>
	</c:forEach>
</body>
</html>