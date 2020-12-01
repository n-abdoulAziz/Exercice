package swa.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import swa.model.Movie;
import swa.web.MovieValidator;

public class TestMovieValidator {

	@Test
	public void testMovieValidator() {
		Movie m = new Movie(100, "  ", 1500, "Une description");
		MovieValidator v = new MovieValidator();
		v.validate(m);
		assertEquals(v.getErrors().size(), 2);
		assertNotNull(v.getErrors().get("name"));
		assertNotNull(v.getErrors().get("year"));
	}

	@Test
	public void testNoErrorMovieValidator() {
		Movie m = new Movie(100, "Hello", 2000, "Une description");
		MovieValidator v = new MovieValidator();
		v.validate(m);
		assertEquals(v.getErrors().size(), 0);
	}

}
