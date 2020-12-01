package swa.web;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.GenericValidator;

import swa.dao.IMovieDao;
import swa.dao.JpaMoviesDao;
import swa.model.Movie;

/**
 * Une servlet pour gérer les films.
 */
@WebServlet(//
		loadOnStartup = 1, //
		name = "movieServlet", //
		description = "Servlet des gestion des films", //
		urlPatterns = { "/hello", "/movies", "/movie/*", "/edit-movie/*" } //
)
public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	IMovieDao dao;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		dao = new JpaMoviesDao();
		System.out.println("Start " + this);
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		dao.close();
	}

	/**
	 * Une simple page de test (GET)
	 */
	private String hello(HttpServletRequest request) {
		return "/swa/hello.jsp";
	}

	/**
	 * Montrer les films (GET)
	 */
	private String getMovies(HttpServletRequest request) {
		request.setAttribute("movies", dao.findAllMovies());
		return "/swa/movies.jsp";
	}

	/**
	 * Montrer un film (GET)
	 */
	private String getMovie(HttpServletRequest request) {
		int idMovie = Integer.parseInt(request.getPathInfo().substring(1));
		request.setAttribute("movie", dao.findMovie(idMovie));
		return "/swa/show-movie.jsp";
	}

	/**
	 * Editer un film (GET)
	 */
	private String getEditMovie(HttpServletRequest request) {
		int idMovie = Integer.parseInt(request.getPathInfo().substring(1));
		request.setAttribute("movie", dao.findMovie(idMovie));
		return "/swa/edit-movie.jsp";
	}

	/**
	 * Editer un film (POST)
	 */
	private String postEditMovie(HttpServletRequest request) throws ServletException, IOException {
		String view = "/swa/edit-movie.jsp";
		int idMovie = Integer.parseInt(request.getPathInfo().substring(1));
		MovieValidator mv = new MovieValidator();
		Movie movie = dao.findMovie(idMovie);
		request.setCharacterEncoding("UTF-8");
		movie.setName(request.getParameter("name"));
		movie.setDescription(request.getParameter("description"));
		if (GenericValidator.isInt(request.getParameter("year"))) {
			movie.setYear(Integer.parseInt(request.getParameter("year")));
		} else {
			mv.getErrors().put("year", "L'année doit être un entier");
		}
		mv.validate(movie);
		if (mv.getErrors().size() > 0) {
			request.setAttribute("errors", mv.getErrors());
			request.setAttribute("movie", movie);
		} else {
			dao.saveMovie(movie);
			request.setAttribute("movie", movie);
			view = "/swa/show-movie.jsp";
		}
		return view;
	}

	/**
	 * Requêtes
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String view = "/swa/index.jsp";
		switch (request.getMethod() + "-" + request.getServletPath()) {
		case "GET-/hello":
			view = hello(request);
			break;
		case "GET-/movies":
			view = getMovies(request);
			break;
		case "GET-/movie":
			view = getMovie(request);
			break;
		case "GET-/edit-movie":
			view = getEditMovie(request);
			break;
		case "POST-/edit-movie":
			view = postEditMovie(request);
			break;
		default:
			throw new ServletException("bad request");
		}
		request.getServletContext().getRequestDispatcher(view).forward(request, response);
	}

}
