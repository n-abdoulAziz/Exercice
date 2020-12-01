package swa.dao;

import java.util.Collection;

import swa.model.Movie;

public interface IMovieDao {

	Collection<Movie> findAllMovies();
	Movie findMovie(int id);
	void saveMovie(Movie m);
	void close();

}
