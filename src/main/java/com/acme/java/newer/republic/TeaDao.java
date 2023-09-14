package com.acme.java.newer.republic;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class TeaDao {

	private final NotificationService notificationService;
	private final I18nService i18nService;

	TeaDao(NotificationService notificationService, I18nService i18nService) {
		this.notificationService = notificationService;
		this.i18nService = i18nService;
	}

	public List<Tea> getTea() throws URISyntaxException, InterruptedException {
		return new RestDaoTemplate().getItems("https://beverages-maker.acme.com/tea",
		                                      this::mapBodyToTea,
		                                      this::noResultFound,
		                                      this::getExceptionMessage,
		                                      notificationService::notifyTeasAvailability);
	}

	private String getExceptionMessage() {
		return i18nService.getMessage("fr", "exception.noTea");
	}

	private boolean noResultFound(IOException ioException) {
		return ioException.getMessage().contains("404");
	}

	private List<Tea> mapBodyToTea(String requestBody) {
		return List.of();
	}

}