<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
<h1>Détails d'un film</h1>

<table class="table">
	<tr>
		<th>Name :</th>
		<td>${movie.name}</td>
	</tr>
	<tr>
		<th>Year :</th>
		<td>${movie.year}</td>
	</tr>
	<tr>
		<th>Description :</th>
		<td>${movie.description}</td>
		<td></td>
	</tr>
</table>

<c:url var="editAction" value="/edit-movie"/>

<p>
	<a class="btn btn-primary btn-sm" href="${editAction}/${movie.id}">Modifier</a>
</p>
</div>
