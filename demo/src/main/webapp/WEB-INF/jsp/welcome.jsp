<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Pet Ownership</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Pet Ownership Application</h1>
		</div>
		
		<form action="/" class="" id=""  method="GET">
        <div class="button-group">
            <button type="submit" class="btn btn-primary">MATCH DIFFERENT PETS!</button>
        </div>
		</form>
		<div align="center">
        <table class="table table-hover">
            <caption><h2>List of Customers</h2></caption>
            <tr>
                <th>CUSTOMER ID</th>
                <th>CUSTOMER NAME</th>
                <th>ANIMAL NAMES (Animal names are separated by ' --- ')</th>
                <th>GENDER</th>
            </tr>
            <c:forEach var="responseList" items="${responseList}">
                <tr>
                    <td><c:out value="${responseList.customerId}" /></td>
                    <td><c:out value="${responseList.customerName}" /></td>
                    <td><c:out value="${responseList.animalNames}" /></td>
                    <td><c:out value="${responseList.gender}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
