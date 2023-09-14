package com.acme.java.newer.republic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class CoffeeDao {

	private final I18nService i18nService;

	public CoffeeDao(I18nService i18nService) {
		this.i18nService = i18nService;
	}

	public List<Coffee> getCoffee() throws URISyntaxException, InterruptedException {
		return new RestDaoTemplate().getItems("https://beverages-maker.acme.com/coffee",
		                                      this::mapBodyToCoffee,
		                                      this::noResultFound,
		                                      this::getExceptionMessage,
		                                      _ -> {});
	}

	private String getExceptionMessage() {
		return i18nService.getMessage("fr", "exception.noCoffee");
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("NOT_FOUND");
	}

	private List<Coffee> mapBodyToCoffee(String requestBody) {
		return List.of();
	}

}