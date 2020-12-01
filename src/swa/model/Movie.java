package swa.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@NamedQuery(name = "findAllMovies", query = "SELECT m FROM Movie m")

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id = 0;

	@Basic
	private String name;

	@Basic
	private int year;

	@Basic
	private String description;

	public Movie() {
		super();
	}

	public Movie(int id, String name, int year, String description) {
		super();
		this.id = id;
		this.name = name;
		this.year = year;
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getYear() {
		return year;
	}

	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format("Movie [id=%s, name=%s, year=%s, description=%s]", id, name, year, description);
	}

}
