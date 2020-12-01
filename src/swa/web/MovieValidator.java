package swa.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.validator.GenericValidator;

import swa.model.Movie;

public class MovieValidator {

	Map<String, String> errors = new HashMap<>();

	public void validate(Movie m) {

		if (GenericValidator.isBlankOrNull(m.getName())) {
			errors.put("name", "Le nom est obligatoire");
		}

		if (!GenericValidator.isInRange(m.getYear(), 1900, 2100)) {
			errors.put("year", "L'année doit être compris entre 1900 et 2100");
		}

		if (!GenericValidator.maxLength(m.getDescription(), 150)) {
			errors.put("description", "La description est limitée à 150 caractères");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

}
