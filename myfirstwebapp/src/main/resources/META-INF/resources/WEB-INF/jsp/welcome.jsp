<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>

<body>
	<div class="container">
		<h1>Welcome!</h1>
		<hr>
		<div>Your Name: ${name}</div>
		<div><a href="list-todos">Manage your Todos</a></div>
	</div>
</body>
<%@ include file="common/footer.jsp" %>
</html>