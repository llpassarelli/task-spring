<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<meta http-equiv="refresh" content="5" />
<jsp:include page="../fragments/header.jsp" />

<body>

<div class="container">

<c:if test="${not empty msg}">
	<div class="alert alert-${css} alert-dismissible" role="alert">
		<button type="button" class="close" data-dismiss="alert" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
		<strong>${msg}</strong>
	</div>
</c:if>

<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Gerenciar</h3>
  </div>
  <div class="panel-body">
	<div class="btn-group btn-group-justified" role="group" aria-label="...">	    
	    <spring:url value="/edit/${taskNextId}" var="update" />
		<spring:url value="/delete" var="delete" />
		<div class="btn-group" role="group">
			<button class="btn btn-info btn-lg" onclick="location.href='${update}'">Atender</button>
		</div>
		<div class="btn-group" role="group">
			<button class="btn btn-danger btn-lg" onclick="location.href='${delete}'">Reiniciar</button>
  		</div>
  	</div>
  </div>	
</div>

<c:if test="${!empty taskList}">
    <table class="table table-striped">
    <tr>
        <th width="80">Senha</th>
        <th width="120">Status</th>
        <th width="120">Prioridade</th>
        <th width="60">Data</th>
    </tr>
    <c:forEach items="${taskList}" var="task">
        <tr>
            <td>${task.id}</td>
            <td>${task.status}</td>
            <td>${task.prioridade}</td>
            <td>${task.data}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>
	<jsp:include page="../fragments/footer.jsp" />
</div>
</body>
</html>