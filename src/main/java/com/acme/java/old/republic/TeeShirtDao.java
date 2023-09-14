package com.acme.java.old.republic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class TeeShirtDao {

	private final StatService statService;

	public TeeShirtDao(StatService statService) {
		this.statService = statService;
	}

	public List<TeeShirt> getTeeShirt() throws URISyntaxException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
		                                 .uri(new URI("https://tee-shirts-maker.acme.com/tee"))
		                                 .GET()
		                                 .build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient()
			                                          .send(request, HttpResponse.BodyHandlers.ofString());
			List<TeeShirt> teeShirts = response.body()
			                                   .transform(body -> mapBodyToTeeShirts(body));
			statService.teeShirtInterestByType(teeShirts);
			return teeShirts;
		} catch (IOException e) {
			if (noResultFound(e)) {
				System.out.println("No tee found");
			}
			throw new RuntimeException(e);
		}
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("Nothing here");
	}

	private static List<TeeShirt> mapBodyToTeeShirts(String body) {
		return List.of();
	}

}