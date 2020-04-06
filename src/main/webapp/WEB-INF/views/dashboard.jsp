<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<title><spring:message code="label.title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">

<!-- Bootstrap -->

<spring:url value="resources/css/bootstrap.min.css" var="bootstrapCSS" />
<spring:url value="resources/css/font-awesome.css" var="fontAwesomeCSS" />
<spring:url value="resources/css/main.css" var="mainCSS" />
<spring:url value="https://cdnjs.cloudflare.com/ajax/libs/flag-icon-css/3.3.0/css/flag-icon.min.css" var="flagCSS" />

<link href="${bootstrapCSS}" rel="stylesheet" media="screen">
<link href="${fontAwesomeCSS}" rel="stylesheet" media="screen">
<link href="${mainCSS}" rel="stylesheet" media="screen">
<link href="${flagCSS}" rel="stylesheet" media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div  class="dropdown">
				<a class="dropdown-item" href="?lang=en"><spring:message code="label.english"/></a>
				<a class="dropdown-item" href="?lang=fr"><spring:message code="label.french"/></a>
			</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<c:choose>
				<c:when test="${ search != null }">
					<h1 id="homeTitle">${ nbSearchedComputer }
						<spring:message code="label.searchedComputerFound"/>
						${ search }
					</h1>
				</c:when>
				<c:otherwise>
					<h1 id="homeTitle">
						<b><spring:message code="label.computerDatabase"/> ${ nbRows }
							<spring:message code="label.computerFound"/></b>
					</h1>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${ pageIterator > 0 && pageIterator <= lastPage }">
					<h4>
						<spring:message code="label.page"/>
						${ pageIterator }
					</h4>
				</c:when>
				<c:otherwise>
					<h4>
						<spring:message code="label.noPage"/>
					</h4>
				</c:otherwise>
			</c:choose>

			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code = "label.searchByName"/>"/> <input
							type="submit" id="searchsubmit"
							value="<spring:message code = "label.search"/>"
							class="btn btn-primary"/>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="label.addComputer"/></a><a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="label.editComputer"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>

						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall"/> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><a href="dashboard?columnName=computerName"><spring:message
									code="label.computerName" /></a></th>
						<th><a href="dashboard?columnName=introduced"><spring:message
									code="label.introducedDate" /></a></th>
						<th><a href="dashboard?columnName=discontinued"><spring:message
									code="label.discontinuedDate" /></a></th>
						<th><a href="dashboard?columnName=company"><spring:message
									code="label.company" /></a></th>

					</tr>
				</thead>

				<!-- Browse attribute computers -->

				<tbody id="results">
					<c:choose>
						<c:when test="${search != null}">
							<c:forEach items="${computerDTOSearchedList}" var="computer">
								<tr>
									<td class="editMode"><input type="checkbox" name="cb"
										class="cb" value="${ computer.getIdDTO() }"></td>
									<td><a href="editComputer?id=${ computer.getIdDTO() } "
										onclick=""><c:out value="${ computer.getNameDTO() }"/></a></td>
									<td><c:out value="${ computer.getIntroducedDTO() }"/></td>
									<td><c:out value="${ computer.getDiscontinuedDTO() }"/></td>
									<td><c:out value="${ computer.getCompanyDTO().getNameDTO() }"/></td>
								<tr>
							</c:forEach>

						</c:when>
						<c:otherwise>
							<c:forEach items="${ computerDTOListPag }" var="computer">
								<tr>
									<td class="editMode"><input type="checkbox" name="cb"
										class="cb" value="${computer.getIdDTO()}"></td>
									<td><a href="editComputer?id=${ computer.getIdDTO() }"
										onclick=""> <c:out value="${ computer.getNameDTO() }"/></a></td>
									<td><c:out value="${ computer.getIntroducedDTO() }"/></td>
									<td><c:out value="${ computer.getDiscontinuedDTO() }"/></td>
									<td><c:out value="${ computer.getCompanyDTO().getNameDTO() }"/></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<ul class="pagination">

				<c:choose>
					<c:when test="${ lastPage > 6 }">
						<li><c:choose>
								<c:when test="${ pageIterator >= 2 }">
									<a
										href="dashboard?pageIterator=${ pageIterator - 1 }
										<c:if test="${ search != null }">&search=${ search }</c:if>"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</c:when>
							</c:choose></li>

						<li><a href="dashboard?pageIterator=1"><c:out value="1"/></a></li>

						<c:choose>
							<c:when test="${ pageIterator > 4 }">
								<li><a href="#">...</a></li>
							</c:when>
						</c:choose>

						<c:choose>
							<c:when test="${ pageIterator <= 3 }">
								<c:forEach var="i" begin="2" end="5" step="1">
									<li><a
										href="dashboard?pageIterator=${ i }
									<c:if test="${ search != null }">&search=${ search }</c:if>"><c:out
												value="${ i }" /></a></li>
								</c:forEach>
							</c:when>
							<c:when
								test="${ pageIterator > 3 && pageIterator < lastPage - 3 }">
								<c:forEach var="i" begin="${ pageIterator - 2 }"
									end="${ pageIterator + 2 }" step="1">
									<li><a
										href="dashboard?pageIterator=${ i }
									<c:if test="${ search != null }">&search=${ search }</c:if>">
											<c:out value="${ i }"/>
									</a></li>
								</c:forEach>
							</c:when>
							<c:when test="${ pageIterator >= lastPage - 3 }">
								<c:forEach var="i" begin="${ lastPage - 5}"
									end="${ lastPage -1 }" step="1">
									<li><a
										href="dashboard?pageIterator=${ i }
									<c:if test="${ search != null }">&search=${ search }</c:if>">
											<c:out value="${ i }"/>
									</a></li>
								</c:forEach>
							</c:when>
						</c:choose>


						<c:choose>
							<c:when test="${ pageIterator < lastPage - 3 }">
								<li><a href="#">...</a></li>
							</c:when>
						</c:choose>

						<li><a
							href="dashboard?pageIterator=${ lastPage }
						<c:if test="${ search != null }">&search=${ search }</c:if>">
								<c:out value="${ lastPage }"/>
						</a></li>

						<li><c:choose>
								<c:when test="${ pageIterator != lastPage }">
									<a
										href="dashboard?pageIterator=${ pageIterator + 1 }
									<c:if test="${ search != null }">&search=${ search }</c:if>"
										aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
								</c:when>
							</c:choose></li>
					</c:when>
					<c:otherwise>
						<li><c:choose>
								<c:when test="${ pageIterator >= 2 }">
									<a
										href="dashboard?pageIterator=${ pageIterator - 1 }
									<c:if test="${ search != null }">&search=${ search }</c:if>"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</c:when>
							</c:choose></li>
						<c:forEach var="i" begin="1" end="${ lastPage }" step="1">
							<li><a href="dashboard?pageIterator=${ i }"><c:out
										value="${ i }" /></a></li>
						</c:forEach>
						<li><c:choose>
								<c:when test="${ pageIterator != lastPage }">
									<a
										href="dashboard?pageIterator=${ pageIterator + 1 }
									<c:if test="${ search != null }">&search=${ search }</c:if>"
										aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
								</c:when>
							</c:choose></li>
					</c:otherwise>
				</c:choose>

			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a
					href="dashboard?pageIterator=1&step=10
				<c:if test="${ search != null }">&search=${ search }</c:if>">
					<button type="button" class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">10</button>
				</a><a
					href="dashboard?pageIterator=1&step=50
					<c:if test="${ search != null }">&search=${ search }</c:if>">
					<button type="button" class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">50</button>
				</a><a
					href="dashboard?pageIterator=1&step=100
					<c:if test="${ search != null }">&search=${ search }</c:if>">
					<button type="button" class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">100</button>
				</a>
			</div>
		</div>

	</footer>
	<spring:url value="resources/js/jquery.min.js" var="jqueryJS" />
	<spring:url value="resources/js/bootstrap.min.js" var="bootsrapJS" />
	<spring:url value="resources/js/dashboard.js" var="dashboardJS" />

	<script src="${jqueryJS}"></script>
	<script src="${bootstrapJS}"></script>
	<script src="${dashboardJS}"></script>

</body>
</html>