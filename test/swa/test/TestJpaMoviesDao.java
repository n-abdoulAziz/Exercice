package swa.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import swa.dao.IMovieDao;
import swa.dao.JpaMoviesDao;
import swa.model.Movie;

public class TestJpaMoviesDao {

	IMovieDao dao = new JpaMoviesDao();

	@Test
	public void testFindAllMovies() {
		assertTrue(dao.findAllMovies().size() > 0);
	}

	@Test
	public void testAddMovie() {
		var m = new Movie(0, "Nouveau", 2020, "Une description");
		dao.saveMovie(m);
		assertTrue(m.getId() > 0);
		var m2 = dao.findMovie(m.getId());
		assertNotEquals(m, m2);
		assertEquals(m.getName(), m2.getName());
	}

}
