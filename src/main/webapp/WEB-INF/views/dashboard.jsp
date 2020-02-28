<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
			<a class="navbar-brand" href="dashboard.jsp"> Application -
				Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<b>Computer Database : ${nbRows} computers found</b> 
			</h1>
			<c:choose>
				<c:when test="${pageIterator > 0 && pageIterator <= lastPage}">
					<h4>Page ${pageIterator}</h4>
				</c:when>
				<c:otherwise>
					<h4>No page found</h4>
				</c:otherwise>
			</c:choose>

			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer.html">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
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
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				
				<!-- Browse attribute computers -->
				<tbody id="results">
					<c:forEach items="${listComputerPag}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="0"></td>
							<td><a href="EditComputer.jsp" onclick=""> <c:out
										value="${computer.getName()}"></c:out></a></td>
							<td><c:out value="${computer.getIntroduced()}"></c:out></td>
							<td><c:out value="${computer.getDiscontinued()}"></c:out></td>
							<td><c:out value="${computer.getCompany().getName()}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">

			<ul class="pagination">

				<c:choose>
					<c:when test="${ lastPageIndex > 6 }">
						<li><c:choose>
								<c:when test="${ pageIterator >= 2 }">
									<a href="dashboard?pageIterator=${ pageIterator - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</c:when>
							</c:choose></li>

						<li><a href="dashboard?pageIterator=1"><c:out value="1" /></a></li>

						<c:choose>
							<c:when test="${ pageIterator > 4 }">
								<li><a href="#">...</a></li>
							</c:when>
						</c:choose>

						<c:choose>
							<c:when test="${ pageIterator <= 3 }">
								<c:forEach var="i" begin="2" end="5" step="1">
									<li><a href="dashboard?pageIterator=${ i }"><c:out
												value="${ i }" /></a></li>
								</c:forEach>
							</c:when>
							<c:when
								test="${ pageIterator > 3 && pageIterator < lastPageIndex - 3 }">
								<c:forEach var="i" begin="${ pageIterator - 2 }"
									end="${ pageIterator + 2 }" step="1">
									<li><a href="dashboard?pageIterator=${ i }"><c:out
												value="${ i }" /></a></li>
								</c:forEach>
							</c:when>
							<c:when test="${ pageIterator >= lastPageIndex - 3 }">
								<c:forEach var="i" begin="${ lastPageIndex - 5}"
									end="${ lastPageIndex -1 }" step="1">
									<li><a href="dashboard?pageIterator=${ i }"> <c:out
												value="${ i }" /></a></li>
								</c:forEach>
							</c:when>
						</c:choose>


						<c:choose>
							<c:when test="${ pageIterator < lastPageIndex - 3 }">
								<li><a href="#">...</a></li>
							</c:when>
						</c:choose>

						<li><a href="dashboard?pageIterator=${ lastPageIndex }"><c:out
									value="${ lastPageIndex }" /></a></li>

						<li><c:choose>
								<c:when test="${ pageIterator != lastPageIndex }">
									<a href="dashboard?pageIterator=${ pageIterator + 1 }"
										aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
								</c:when>
							</c:choose></li>
					</c:when>
					<c:otherwise>
						<li><c:choose>
								<c:when test="${ pageIterator >= 2 }">
									<a href="dashboard?pageIterator=${ pageIterator - 1 }"
										aria-label="Previous"> <span aria-hidden="true">&laquo;</span></a>
								</c:when>
							</c:choose></li>
						<c:forEach var="i" begin="1" end="${ lastPageIndex }" step="1">
							<li><a href="dashboard?pageIterator=${ i }"><c:out
										value="${ i }" /></a></li>
						</c:forEach>
						<li><c:choose>
								<c:when test="${ pageIterator != lastPageIndex }">
									<a href="dashboard?pageIterator=${ pageIterator + 1 }"
										aria-label="Next"><span aria-hidden="true">&raquo;</span></a>
								</c:when>
							</c:choose></li>
					</c:otherwise>
				</c:choose>

				</ul> 
								
			<div class="btn-group btn-group-sm pull-right" role="group">
				<a href="dashboard?pageIterator=1&step=10"><button type="button"
						class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">10</button></a> <a
					href="dashboard?pageIterator=1&step=50"><button type="button"
						class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">50</button></a> <a
					href="dashboard?pageIterator=1&step=100"><button type="button"
						class="btn btn-default"
						onclick="<c:set var="pageIterator" value="1"/>">100</button></a>
			</div>
		</div>

	</footer>
	<script src="resources/js/jquery.min.js"></script>
	<script src="resources/js/bootstrap.min.js"></script>
	<script src="resources/js/dashboard.js"></script>

</body>
</html>