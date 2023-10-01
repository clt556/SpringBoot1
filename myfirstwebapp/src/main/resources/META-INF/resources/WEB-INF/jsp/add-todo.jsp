<%@ include file="common/header.jsp" %>
<%@ include file="common/navigation.jsp" %>
<body>
	
	<div class="container">
		<h1>Enter Todo Details</h1>
		<form:form method="post" modelAttribute="todo">
			<fieldset class="mb-3">
				<form:label path="description">Description</form:label>
				<form:input type="text" path="description" name="description" required="required"/>
				<form:errors path="description" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="mb-3">
				<form:label path="targetDate">Target Date</form:label>
				<form:input type="text" path="targetDate" name="description" required="required"/>
			</fieldset>	
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="done"/>
			<input type="submit" class="btn btn-success" />
		</form:form>
	</div>
	<%@ include file="common/footer.jsp" %>
	<script type="text/javascript">
		$('#targetDate').datepicker({
		    format: 'yyyy-mm-dd'
		});
	</script>
</body>
</html>