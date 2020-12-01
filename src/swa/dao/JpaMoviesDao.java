package swa.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import swa.model.Movie;

public class JpaMoviesDao implements IMovieDao {

	/*
	 * Usine à fabriquer les EntityManager.
	 */
	EntityManagerFactory emf = null;

	public JpaMoviesDao() {
		emf = Persistence.createEntityManagerFactory("moviesDatabase");
		populateData();
	}

	/*
	 * Fermeture du service.
	 */
	@Override
	public void close() {
		emf.close();
	}

	/*
	 * Création des données initiales.
	 */
	private void populateData() {
		if (findAllMovies().size() == 0) {
			saveMovie(new Movie(100, "Star wars 4", 1977, //
					"Il y a bien longtemps, dans une galaxie " + //
							"lointaine, très lointaine..."));
			saveMovie(new Movie(200, "Star wars 5", 1980, //
					"Le temps du péril a commencé pour la rébellion..."));
			saveMovie(new Movie(300, "Star wars 6", 1983, //
					"Luke Skywalker est retourné parmi les siens sur la " + //
							"planète Tatooine..."));
		}
	}

	/*
	 * Architecture classique des méthodes JPA avec gestion
	 * explicite de l'EntityManager.
	 */
	@Override
	public Collection<Movie> findAllMovies() {
		// Création d'un EntityManager (qui n'est pas thread-safe)
		EntityManager em = emf.createEntityManager();
		try {
			// ouverture d'un transaction
			em.getTransaction().begin();

			// création de la requête
			TypedQuery<Movie> q = em.createNamedQuery("findAllMovies", Movie.class);

			// exécution et récupération du résultat
			List<Movie> result = q.getResultList();

			// fermeture de la transaction
			em.getTransaction().commit();
			return result;
		} finally {
			// fermeture de l'entity manager
			em.close();
		}
	}

	/*
	 * Architecture simplifiée basée sur l'utilisation du try-with-resources
	 */
	@Override
	public Movie findMovie(int id) {
		try (var em = new AutoCloseableEntityManager(emf)) {
			return em.find(Movie.class, id);
		}
	}

	/*
	 * Même architecture simplifiée.
	 */
	@Override
	public void saveMovie(Movie movie) {
		try (var em = new AutoCloseableEntityManager(emf)) {
			if (movie.getId() > 0) {
				em.merge(movie);
			} else {
				em.persist(movie);
			}
			em.commit();
			System.out.printf("Save %s\n", movie);
		}
	}

}
