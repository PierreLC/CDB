<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.computerDatabase" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->

<link href="resources/css/bootstrap.min.css" rel="stylesheet"
	media="screen">
<link href="resources/css/font-awesome.css" rel="stylesheet"
	media="screen">
<link href="resources/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard.html"><spring:message
					code="label.title" /></a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">
						<spring:message code="label.id" />
						${ computerDTOId }
					</div>
					<h1>
						<spring:message code="label.edit" />
					</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="${ computerDTO }" id="computerId"
							name="computerId" />

						<!-- TODO: Change this value with the computer id -->

						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message
										code="label.computerName" /></label> <input type="text"
									class="form-control" id="computerName"
									placeholder="Computer name" name="computerName"
									value="${ computerDTOName }">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message
										code="introducedDate" /></label> <input type="date"
									class="form-control" id="introduced"
									placeholder="Introduced date" name="introduced"
									value="${ introducedDTO }">
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message
										code="label.discontinuedDate" /></label> <input type="date"
									class="form-control" id="discontinued"
									placeholder="Discontinued date" name="discontinued"
									value="${ discontinuedDTO }">
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message
										code="label.company" /></label> <select class="form-control"
									id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${ companyDTOList }" var="company">
										<c:choose>
											<c:when test="${ companyId == company.getIdDTO() }">
												<option value="${ company.getIdDTO() }" selected>
												<c:out value="${ company.getNameDTO() }" /></option>
											</c:when>
											<c:otherwise>
												<option value="${ company.getIdDTO() }">
													<c:out value="${ company.getNameDTO() }" /></option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							<spring:message code="label.or" />
							<a href="dashboard.html" class="btn btn-default"><spring:message
									code="label.cancel" /></a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>