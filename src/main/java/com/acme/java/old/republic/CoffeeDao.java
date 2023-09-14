package com.acme.java.old.republic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CoffeeDao {

	private final I18NService i18nService;

	public CoffeeDao(I18NService i18nService) {
		this.i18nService = i18nService;
	}

	public List<Coffee> getCoffee() throws URISyntaxException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
		                                 .uri(new URI("https://beverages-maker.acme.com/coffee"))
		                                 .GET()
		                                 .build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient()
			                                          .send(request, HttpResponse.BodyHandlers.ofString());
			return response.body()
			               .transform(s -> mapBodyToCoffee(s));
		} catch (IOException e) {
			if (noResultFound(e)) {
				System.out.println(getExceptionMessage());
			}
			throw new RuntimeException(e);
		}
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("NOT_FOUND");
	}

	private String getExceptionMessage() {
		return i18nService.getMessage("fr", "exception.noCoffee");
	}

	private List<Coffee> mapBodyToCoffee(String body) {
		return List.of();
	}

}