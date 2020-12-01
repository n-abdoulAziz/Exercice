<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="moviesAction" value="/movies" />

<html lang="fr">
<head>
<meta charset="UTF-8">
<title>Movies application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>

	<nav style="margin-bottom:30px;" class="navbar navbar-light bg-light">
		<a class="navbar-brand" href="${moviesAction}">Liste des films</a> <a
			class="navbar-item" href="https://www.themoviedb.org">The movie
			DB</a>
	</nav>
