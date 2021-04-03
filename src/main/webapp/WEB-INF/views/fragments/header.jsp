<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Task</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<spring:url value="/" var="urlHome" />
<spring:url value="/manager" var="manager" />
<spring:url value="/call" var="call" />


<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">Task</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="${urlHome}">Novo</a></li>
			</ul>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="${call}">Chamadas</a></li>
			</ul>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="${manager}">Gerenciar</a></li>
			</ul>
		</div>
	</div>
</nav>