package com.acme.java.old.republic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TeaDao {

	private final NotificationService notificationService;
	private final I18NService i18nService;

	public TeaDao(NotificationService notificationService, I18NService i18nService) {
		this.notificationService = notificationService;
		this.i18nService = i18nService;
	}

	public List<Tea> getTea() throws URISyntaxException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
		                                 .uri(new URI("https://beverages-maker.acme.com/tea"))
		                                 .GET()
		                                 .build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient()
			                                          .send(request, HttpResponse.BodyHandlers.ofString());
			List<Tea> teas = response.body()
			                         .transform(s -> mapBodyToTea(s));
			notificationService.notifyTeasAvailability(teas);
			return teas;
		} catch (IOException e) {
			if (noResultFound(e)) {
				System.out.println(getExceptionMessage());
			}
			throw new RuntimeException(e);
		}
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("404");
	}

	private String getExceptionMessage() {
		return i18nService.getMessage("fr", "exception.noTea");
	}

	private List<Tea> mapBodyToTea(String body) {
		return List.of();
	}

}