<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
<h1>List of movies</h1>

<table class="table">
	<tr>
		<th>Name</th>
		<th>Year</th>
		<th>Actions</th>
	</tr>
	<c:forEach var="movie" items="${movies}">
		<tr>
			<td>${movie.name}</td>
			<td>${movie.year}</td>
			<td><a class="btn btn-primary btn-sm" href="movie/${movie.id}">Show</a></td>
		</tr>
	</c:forEach>
</table>
</div>
