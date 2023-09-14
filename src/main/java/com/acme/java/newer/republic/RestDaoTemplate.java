package com.acme.java.newer.republic;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class RestDaoTemplate {

	public <T extends Item> List<T> getItems(String url,
	                                         Mapper<T> bodyMapper,
	                                         ErrorResponseTester noResultFound,
	                                         Supplier<String> exceptionMessageSupplier,
	                                         Consumer<List<T>> additionalProcessor) throws URISyntaxException, InterruptedException {

		HttpRequest request = HttpRequest.newBuilder()
		                                 .uri(new URI(url))
		                                 .GET()
		                                 .build();

		try {
			HttpResponse<String> response = HttpClient.newHttpClient()
			                                          .send(request, HttpResponse.BodyHandlers.ofString());
			List<T> list = response.body().transform(bodyMapper);
			additionalProcessor.accept(list);
			return list;
		} catch (IOException e) {
			if (noResultFound.isNoResultFound(e)) {
				System.out.println(exceptionMessageSupplier.get());
			}
			throw new RuntimeException(e);
		}
	}

	public interface Mapper<T extends Item> extends Function<String, List<T>> {
	}

	public interface ErrorResponseTester {

		boolean isNoResultFound(IOException e);

	}

}
