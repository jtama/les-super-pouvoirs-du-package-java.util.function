package com.acme.java.newer.republic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.function.Consumer;

public class TeeShirtDao {

	private final NotificationService notificationService;
	private final StatService statService;

	public TeeShirtDao(NotificationService notificationService, StatService statService) {
		this.notificationService = notificationService;
		this.statService = statService;
	}

	public List<TeeShirt> getTeeShirt() throws URISyntaxException, InterruptedException {
		return new RestDaoTemplate().getItems("https://beverages-maker.acme.com/coffee",
		                                      this::mapBodyToTeeShirts,
		                                      this::noResultFound,
		                                      this::getExceptionMessage,
		                                      makeStatsOnTeeShirts().andThen(notificationService::notifyTeeShirtsAvailability));
	}

	private String getExceptionMessage() {
		return "No tee found";
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("Nothing here");
	}

	private List<TeeShirt> mapBodyToTeeShirts(String requestBody) {
		return List.of();
	}

	private Consumer<List<TeeShirt>> makeStatsOnTeeShirts() {
		return statService::teeShirtInterestByType;
	}

}