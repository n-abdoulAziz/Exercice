<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="container">
	<h1>Modifier un film</h1>

	<form method="post">
		<div class="form-group">
			<label>Nom :</label> <input name="name" class="form-control"
				type="text" value="${movie.name}" required="required" />
			<c:if test="${errors.name != null}">
				<div class="alert alert-danger">
					<c:out value="${errors.name}" />
				</div>
			</c:if>
		</div>
		<div class="form-group">
			<label>Année :</label> <input name="year" class="form-control"
				type="text" value="${movie.year}" required="required" />
			<c:if test="${errors.year != null}">
				<div class="alert alert-danger">
					<c:out value="${errors.year}" />
				</div>
			</c:if>
		</div>
		<div class="form-group">
			<label>Description :</label>
			<textarea name="description" class="form-control" rows="10" cols="50"><c:out
					value="${movie.description}" /></textarea>
			<c:if test="${errors.description != null}">
				<div class="alert alert-danger">
					<c:out value="${errors.description}" />
				</div>
			</c:if>
		</div>
		<button type="submit" class="btn btn-primary">Enregistrer</button>
	</form>
</div>
